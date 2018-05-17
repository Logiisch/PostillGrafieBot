package core;

import commands.cmdAdd;
import commands.cmdOnlineInform;
import java.util.HashMap;
import javax.security.auth.login.LoginException;
import listeners.OrthografieListener;
import listeners.commandListener;
import listeners.onlineListener;
import listeners.readyListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import util.SECRETS;

public class Main
{
  public Main() {}
  
  public static JDABuilder builder = new JDABuilder(AccountType.BOT);
  
  public static void main(String[] args) {
    builder.setToken(SECRETS.TOKEN);
    builder.setAutoReconnect(true);
    builder.setStatus(OnlineStatus.ONLINE);
    String Version = "v" + util.STATIC.VERSION;
    
    builder.setGame(Game.of(Game.GameType.DEFAULT, Version));
    

    addListeners();
    addCommands();
    
    try
    {
      JDA localJDA = builder.buildBlocking();
    } catch (LoginException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public static void addListeners()
  { builder.addEventListener(new commandListener());
    builder.addEventListener(new readyListener());
    builder.addEventListener(new OrthografieListener());
    builder.addEventListener(new onlineListener());
    builder.addEventListener(new guildMemberJoinListener());
  }
  
  public static void addCommands() {
    commandHandler.commands.put("add", new cmdAdd());
    commandHandler.commands.put("online", new cmdOnlineInform());
  }
}
