package core;

import commands.cmdAdd;
import commands.cmdHelp;
import commands.cmdOnlineInform;
import listeners.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import threads.PostillonNewsThread;
import threads.TwitterThread;
import util.SECRETS;
import util.STATIC;

import com.rometools.rome.io.FeedException;

import javax.security.auth.login.LoginException;

public class Main {
    public static JDABuilder builder = new JDABuilder(AccountType.BOT);

    public Main() {
    }

    public static void main(String[] args) {
        builder.setToken(SECRETS.TOKEN);
        builder.setAutoReconnect(true);
        builder.setStatus(OnlineStatus.ONLINE);
        String Version = "-help || v" + util.STATIC.VERSION;

        builder.setGame(Game.of(Game.GameType.DEFAULT, Version));


        addListeners();
        addCommands();

        try {
            JDA localJDA = builder.buildBlocking();
            startThreads(localJDA);
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void addListeners() {
        builder.addEventListener(new commandListener());
        builder.addEventListener(new readyListener());
        builder.addEventListener(new OrthografieListener());
        builder.addEventListener(new onlineListener());
        builder.addEventListener(new guildMemberJoinListener());
    }

    public static void addCommands() {
        commandHandler.commands.put("add", new cmdAdd());
        commandHandler.commands.put("online", new cmdOnlineInform());
        commandHandler.commands.put("help", new cmdHelp());
    }

<<<<<<< HEAD
public class Main
{
  public Main() {}
  public static boolean Testlauf = false;
  
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
=======
    private static void startThreads(JDA jda) {
        new Thread(new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.POSTILLONARTIKEL_AUFKLAERUNG), 105554801L, "Neuer Lügentweet!")).start();
        new Thread(new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.FAKTILLON_AUFKLAEREUNG), 4835416125L, "Neuer Lügenfakt!")).start();
        new Thread(new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.POSTILLLEAKES_BEI_TWITTER), 761646243495964672L, "Neuer Aufklärungstweet!")).start();
>>>>>>> a4d295f31e92de0d43c4f97864ab3e9fda736878
    }
}
