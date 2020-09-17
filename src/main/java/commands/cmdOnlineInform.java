package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class cmdOnlineInform implements Command
{
  public cmdOnlineInform() {}
  
  public boolean called(String[] args, MessageReceivedEvent event)
  {
    return false;
  }
  
  public void action(String[] args, MessageReceivedEvent event) throws java.io.IOException
  {
    ArrayList<String> Empfaenger = new ArrayList();
    try {
      Empfaenger = util.readInTxtFile.Read("onlineListener.txt");
    } catch (Exception e) {
      e.printStackTrace();
      event.getTextChannel().sendMessage("Leider ist ein Fehler aufgetreten, der Admin wurde informiert!").queue();
      return;
    }
    if (Empfaenger.contains(event.getAuthor().getId())) {
      Empfaenger.remove(event.getAuthor().getId());
      try {
        util.printOutTxtFile.Write("onlineListener.txt", Empfaenger);
      } catch (Exception e) {
        e.printStackTrace();
        event.getTextChannel().sendMessage("Leider ist ein Fehler aufgetreten, der Admin wurde informiert!").queue();
        return;
      }
      event.getTextChannel().sendMessage("Du wurdest erfolgreich aus der Liste entfernt!").queue();
    } else {
      Empfaenger.add(event.getAuthor().getId());
      try {
        util.printOutTxtFile.Write("onlineListener.txt", Empfaenger);
      } catch (Exception e) {
        e.printStackTrace();
        event.getTextChannel().sendMessage("Leider ist ein Fehler aufgetreten, der Admin wurde informiert!").queue();
        return;
      }
      event.getTextChannel().sendMessage("Du wurdest erfolgreich zur Liste hinzugefügt!").queue();
    }
  }
  


  public void executed(boolean success, MessageReceivedEvent event) {}
  


  public String help()
  {
    return null;
  }
}
