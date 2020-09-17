package core;

import commands.cmdAdd;
import commands.cmdHelp;
import commands.cmdOnlineInform;
import listeners.*;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
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

        builder.setActivity(Activity.of(Activity.ActivityType.DEFAULT, Version));


        addListeners();
        addCommands();

        try {
            JDA localJDA = builder.build();
            localJDA.awaitReady();
            startThreads(localJDA);
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void addListeners() {
        builder.addEventListeners(new commandListener());
        builder.addEventListeners(new readyListener());
        builder.addEventListeners(new OrthografieListener());
        builder.addEventListeners(new onlineListener());
        builder.addEventListeners(new guildMemberJoinListener());
        builder.addEventListeners(new guildMemberLeaveListener());
    }

    public static void addCommands() {
        commandHandler.commands.put("add", new cmdAdd());
        commandHandler.commands.put("online", new cmdOnlineInform());
        commandHandler.commands.put("help", new cmdHelp());
    }


    private static void startThreads(JDA jda) {
        /*new Thread(new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.POSTILLONARTIKEL_AUFKLAERUNG), "Der_Postillon", "Neuer Lügentweet!"), "Postillon-Twitter-Thread").start();
        new Thread(new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.FAKTILLON_AUFKLAEREUNG), "Faktillon", "Neuer Lügenfakt!"), "Faktillon-Twitter-Thread").start();*/
        new Thread(new TwitterThread(jda.getTextChannelById(STATIC.CHANNEL.POSTILLLEAKES_BEI_TWITTER), "postillleaks", "Neuer Aufklärungstweet!"), "Postillleaks-Twitter-Thread").start();
    }
}
