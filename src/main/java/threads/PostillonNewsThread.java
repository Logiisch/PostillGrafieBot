package threads;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import org.apache.commons.text.StringEscapeUtils;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class PostillonNewsThread implements Runnable {

    private static final String url = "https://feeds.feedburner.com/blogspot/rkEL";
    private Date lastEntry = new Date();
    private TextChannel channel;

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                try {
                    Thread.sleep(10000);
                    SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
                    for (SyndEntry e : feed.getEntries()) {
                        if (e.getPublishedDate().after(lastEntry)) {
                            System.out.println(e);
                            System.out.println(e.getAuthor().length() - 1);
                            lastEntry = e.getPublishedDate();
                            channel.sendMessage(new EmbedBuilder()
                                    .setTitle(e.getTitle(), e.getLink())
                                    .setAuthor(e.getAuthor().substring(21, 34))
                                    .setColor(Color.YELLOW)
                                    .setImage(e.getForeignMarkup().get(0).getAttribute("url").getValue())
                                    .setDescription(StringEscapeUtils.unescapeHtml4(
                                            e.getDescription()
                                                    .getValue()
                                                    .substring(e.getDescription().getValue().indexOf("</div>") + 6, e.getDescription().getValue().indexOf("<br>")))
                                            .replace("<i>", "*")
                                            .replace("</i>", "*")
                                            .replace("<b>", "**")
                                            .replace("</b>", "**")
                                    )
                                    .setFooter("Neue Lügengeschichte veröffentlicht!", "https://4.bp.blogspot.com/-46xU6sntzl4/UVHLh1NGfwI/AAAAAAAAUlY/RiARs4-toWk/s800/Logo.jpg")
                                    .build()).queue();
                        }
                    }
                    Thread.sleep(50000);
                } catch (FeedException e) {
                    System.err.println(e.getMessage());
                } catch (InterruptedException e) {
                    return;
                } catch (IOException e) {
                    System.err.println("Got IOExcetion. Please check network!\n" + e.getMessage());
                }
            }
        }
    }

    public void setChannel(TextChannel channel) {
        this.channel = channel;
    }
}
