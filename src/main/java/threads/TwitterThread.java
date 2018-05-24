package threads;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import util.ParseUtil;
import util.SECRETS;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class TwitterThread implements Runnable {

    private static final OAuth10aService service = new ServiceBuilder(SECRETS.TWITTER_API_KEY)
            .apiSecret(SECRETS.TWITTER_API_SECRET)
            .build(TwitterApi.instance());
    private static final OAuth1AccessToken token = new OAuth1AccessToken(SECRETS.TWITTER_ACCESS_TOKEN, SECRETS.TWITTER_ACCESS_TOKEN_SECRET);

    private final TextChannel channel;
    private final String screenName;
    private final String footer;
    private Date lastTweet = new Date(System.currentTimeMillis());

    public TwitterThread(TextChannel channel, String screenName, String footer) {
        this.channel = channel;
        this.screenName = screenName;
        this.footer = footer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);

                final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/statuses/user_timeline.json");
                request.addParameter("screen_name", screenName);
                request.addParameter("tweet_mode", "extended");
                service.signRequest(token, request);
                final Response response = service.execute(request);

                JSONArray array = new JSONArray(response.getBody());
                JSONObject json = array.getJSONObject(0);

                Date created_at = ParseUtil.getDate("created_at", array.getJSONObject(0));
                if (created_at.after(lastTweet)) {
                    lastTweet = created_at;
                    if ((json.isNull("in_reply_to_screen_name") || json.getString("in_reply_to_screen_name").equalsIgnoreCase(screenName)) && !json.has("retweeted_status")) {
                        String text = StringEscapeUtils.unescapeHtml4(json.getString("full_text"));
                        String media = null;
                        if (json.getJSONObject("entities").has("media")) {
                            media = json.getJSONObject("entities").getJSONArray("media").getJSONObject(0).getString("media_url_https");
                            //TODO: multiple Images
                        }
                        String user = json.getJSONObject("user").getString("name") + " (@" + json.getJSONObject("user").getString("screen_name") + ")";
                        String userURL = ParseUtil.getRawString("url", json.getJSONObject("user"));
                        String userImageURL = ParseUtil.getRawString("profile_image_url", json.getJSONObject("user"));
                        String thumb = ParseUtil.getRawString("profile_banner_url", json.getJSONObject("user"));

                        channel.sendMessage(new EmbedBuilder().setColor(new Color(53, 137, 255))
                                .setAuthor(user, userURL, userImageURL)
                                .setDescription(text)
                                .setImage(media)
                                .setThumbnail(thumb)
                                .setFooter(footer, null).build()).queue();
                    }

                }
            } catch (InterruptedException e) {
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("Got IOException. Please check network!\n" + e.getMessage());
            }
        }
    }
}
