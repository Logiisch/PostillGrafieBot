package listeners;

import core.commandHandler;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.STATIC;

import java.io.IOException;
import java.util.HashMap;

public class commandListener extends ListenerAdapter {
  //public static HashMap<User, String> Blocked = new HashMap<>();
  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    String CD = event.getMessage().getContentDisplay().toLowerCase();

    if (CD.startsWith(STATIC.PREFIX) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
      try {
        commandHandler.handleCommand(commandHandler.parser.parse(CD, event), event);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

}