package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public abstract interface Command
{
  public abstract boolean called(String[] paramArrayOfString, MessageReceivedEvent paramMessageReceivedEvent);
  
  public abstract void action(String[] paramArrayOfString, MessageReceivedEvent paramMessageReceivedEvent)
    throws IOException;
  
  public abstract void executed(boolean paramBoolean, MessageReceivedEvent paramMessageReceivedEvent);
  
  public abstract String help();
}
