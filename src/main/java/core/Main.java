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

    private static void startThreads(JDA jda) {
        PostillonNewsThread p = new PostillonNewsThread();
        p.setChannel(jda.getTextChannelById(STATIC.CHANNEL.POSTILLONARTIKEL_AUFKLAERUNG));
        new Thread(p).start();
        new Thread(new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.POSTILLONARTIKEL_AUFKLAERUNG), 105554801L, "Neuer Lügentweet!")).start();
        new Thread(new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.FAKTILLON_AUFKLAEREUNG), 4835416125L, "Neuer Lügenfakt!")).start();
        new Thread(new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.POSTILLLEAKES_BEI_TWITTER), 761646243495964672L, "Neuer Aufklärungstweet!")).start();
    }
}
