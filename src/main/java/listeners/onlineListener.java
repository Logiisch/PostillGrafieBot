package listeners;

import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;

import java.util.ArrayList;

public class onlineListener extends net.dv8tion.jda.api.hooks.ListenerAdapter
{
  public onlineListener() {}
  
  public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event)
  {
    if (!event.getUser().getId().equalsIgnoreCase("446224496869900292")) {
      return;
    }
    String alt = event.getOldOnlineStatus().name();
    String neu = event.getNewOnlineStatus().name();
    if ((!alt.equalsIgnoreCase("offline")) && (!neu.equalsIgnoreCase("offline"))) {
      return;
    }
    
    ArrayList<String> Empfaenger;
    try {
      Empfaenger = util.readInTxtFile.Read("onlineListener.txt");
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
    
    if (alt.equalsIgnoreCase("offline")) {
      for (String id : Empfaenger) {
        event.getJDA().getUserById(id).openPrivateChannel().complete().sendMessage("Postillleaks ist nun online!").queue();
      }
    } else {
      for (String id : Empfaenger) {
        event.getJDA().getUserById(id).openPrivateChannel().complete().sendMessage("Postillleaks ist nun offline!").queue();
      }
    }
  }
}
