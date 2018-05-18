package listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;

import java.awt.*;
import java.util.Date;
import java.util.Iterator;

public class readyListener extends net.dv8tion.jda.core.hooks.ListenerAdapter {
  public readyListener() {}
  
  public void onReady(net.dv8tion.jda.core.events.ReadyEvent event) {
    String out = "\nDer Bot läuft aktuell hier:\n";
    for (Guild g : event.getJDA().getGuilds()) {
      out += g.getName() + "\n";
    }


    System.out.println(out);

    EmbedBuilder msg = new EmbedBuilder().setColor(Color.CYAN)
            .setTitle("Compiled")
            .setDescription("New session started")
            .setFooter("\uD83D\uDD51 " + new Date().toString(), "https://pbs.twimg.com/profile_images/935191638149881858/aB5F6zh4_400x400.jpg");

    for (Guild g: event.getJDA().getGuilds()) {
        try {
            g.getTextChannelById("447013295686418434").sendMessage(msg.build()).queue();
        } catch (Exception e)
        {

        }
    }


    System.out.println("Zum Beenden Strg + C !");
  }
}
