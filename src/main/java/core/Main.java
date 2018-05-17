package core;

import commands.cmdAdd;
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
        String Version = "v" + util.STATIC.VERSION;

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
        builder.addEventListener(new UserListener());
    }

    public static void addCommands() {
        commandHandler.commands.put("add", new cmdAdd());
        commandHandler.commands.put("online", new cmdOnlineInform());
    }

    private static void startThread(JDA jda) {
        PostillonNewsThread postillonNewsThread = new PostillonNewsThread();
        postillonNewsThread.setChannel(jda.getTextChannelById(STATIC.CHANNEL.POSTILLONARTIKEL_AUFKLAERUNG));
        new Thread(postillonNewsThread).start();
        TwitterThread postillon = new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.POSTILLONARTIKEL_AUFKLAERUNG), 105554801L, "Neuer Lügentweet!");
        new Thread(postillon).start();
        TwitterThread postillleaks = new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.POSTILLLEAKES_BEI_TWITTER), 761646243495964672L, "Neuer Qualitätstweet!");
        new Thread(postillleaks).start();
        TwitterThread faktillon = new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.FAKTILLON_AUFKLAEREUNG), 4835416125L, "Neue Lügenfakten!");
        new Thread(faktillon).start();
    }
}
