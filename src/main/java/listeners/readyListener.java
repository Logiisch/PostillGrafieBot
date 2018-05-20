package listeners;

import net.dv8tion.jda.core.entities.Guild;

import java.util.Iterator;

public class readyListener extends net.dv8tion.jda.core.hooks.ListenerAdapter {
  public readyListener() {}
  
  public void onReady(net.dv8tion.jda.core.events.ReadyEvent event) {
    String out = "\nDer Bot läuft aktuell hier:\n";
    for (Guild g : event.getJDA().getGuilds()) {
      out += g.getName() + "\n";
    }
OrthografieListener.loadFehler(event.getJDA());

    System.out.println(out);
    for (Guild g : event.getJDA().getGuilds()) {

    }


    System.out.println("Zum Beenden Strg + C !");
  }
}
