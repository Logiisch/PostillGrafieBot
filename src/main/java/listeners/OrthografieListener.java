package listeners;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class OrthografieListener extends ListenerAdapter {
    public static HashMap<User, Integer> Anzahl = new HashMap<>();
    String[] Postillon = new String[]{"postillion", "postillong", "postillohn", "postilliong", "postilliohn", "postiilliion", "postilllon", "postilllion", "posstillon", "posstillion", "posttillon", "posttillion", "possttillon", "possttillion", "postiliion", "postilion", "postilon", "postilllllloioioin", "poiltion", "postillibong", "Postillbong"};
    String[] Postillleaks = new String[]{"postilleaks", "postileaks", "postileks", "postillleks", "posillleaks"};

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User u;
        String[] einzeln = event.getMessage().getContentDisplay().toLowerCase().split(" ");
        boolean FPostillon = false;
        boolean FPostillleaks = false;
        for (String word : einzeln) {
            if (Arrays.stream(this.Postillon).parallel().anyMatch(word::contains)) {
                FPostillon = true;
            }
            if (Arrays.stream(this.Postillleaks).parallel().anyMatch(word::contains)) {
                FPostillleaks = true;
            }
        }
        if (FPostillon && FPostillleaks) {
            event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + " kann weder das Wort ``Postillon`` noch das Wort ``Postillleaks`` richtig schreiben!").queue();
            u = event.getAuthor();
            addFehler(u, 2, event.getTextChannel());
            return;
        }
        if (FPostillleaks) {
            event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + " kann das Wort ``Postillleaks`` anscheinend nicht richtig schreiben!").queue();
            u = event.getAuthor();
            addFehler(u, 1, event.getTextChannel());
        }
        if (FPostillon) {
            event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + " kann das Wort ``Postillon`` anscheinend nicht richtig schreiben!").queue();
            u = event.getAuthor();
            addFehler(u, 1, event.getTextChannel());
        }
    }

    private void addFehler(User u, Integer Menge, TextChannel c) {
        if (!Anzahl.containsKey(u)) {
            Anzahl.put(u, 0);
        }
        int Fehlerzahl = Anzahl.get(u);
        Fehlerzahl += Menge;
        Anzahl.put(u, Fehlerzahl);
        if (Fehlerzahl > 2) {
            c.sendMessage("Du solltest echt bestraft werden, das ist schon dein " + Fehlerzahl + ". Fehler!").queue();
        }
    }
}