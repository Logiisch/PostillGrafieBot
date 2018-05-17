package threads;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.*;
import java.util.Date;

public class TwitterThread implements Runnable {

    private static final Twitter twitter;

    static {
        ConfigurationBuilder builder = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");
        twitter = new TwitterFactory(builder.build()).getInstance();
    }

    private final TextChannel channel;
    private final long userId;
    private final String footer;
    private Date lastTweet = new Date();

    public TwitterThread(TextChannel channel, long userId, String footer) {
        this.channel = channel;
        this.userId = userId;
        this.footer = footer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ResponseList<Status> response = twitter.getUserTimeline(userId);
                for (Status status : response) {
                    if (status.getCreatedAt().after(lastTweet)) {
                        lastTweet = status.getCreatedAt();
                        String content = status.getText();
                        if (!status.isRetweet() && !(content.startsWith("@")))
                            sendMessage(status);
                    }
                }
                Thread.sleep(60000);
            } catch (TwitterException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    protected void sendMessage(Status status) {
        channel.sendMessage(new EmbedBuilder().setColor(new Color(53, 137, 255))
                .setAuthor(status.getUser().getScreenName(), status.getUser().getURL(), status.getUser().getProfileImageURL())
                .setDescription(status.getText())
                .setImage(status.getMediaEntities().length > 0 ? status.getMediaEntities()[0].getType().equals("photo") ? status.getMediaEntities()[0].getMediaURL() : null : null)
                .setThumbnail(status.getUser().getProfileBannerURL())
                .setFooter(footer, null).build()).queue();

    }
}