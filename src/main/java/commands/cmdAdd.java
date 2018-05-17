package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdAdd implements Command
{
  public cmdAdd() {}
  
  public boolean called(String[] args, MessageReceivedEvent event) {
    return false;
  }
  
  public void action(String[] args, MessageReceivedEvent event) throws java.io.IOException
  {
    if (args.length < 1) {
      event.getTextChannel().sendMessage("Benutzung: -add [Falsche Schreibweise]").queue();
      return;
    }
    String wort = args[0];
    System.out.println("Vorschlag: ,\"" + wort.toLowerCase() + "\"");
    event.getTextChannel().sendMessage("Vorschlag aufgenommen! Wird demnächst eingefügt.").queue();
  }
  


  public void executed(boolean success, MessageReceivedEvent event) {}
  

  public String help()
  {
    return null;
  }
}
