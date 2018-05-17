package commands;

import java.io.IOException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract interface Command
{
  public abstract boolean called(String[] paramArrayOfString, MessageReceivedEvent paramMessageReceivedEvent);
  
  public abstract void action(String[] paramArrayOfString, MessageReceivedEvent paramMessageReceivedEvent)
    throws IOException;
  
  public abstract void executed(boolean paramBoolean, MessageReceivedEvent paramMessageReceivedEvent);
  
  public abstract String help();
}
