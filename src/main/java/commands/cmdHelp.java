package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class cmdHelp implements Command {

    EmbedBuilder message = new EmbedBuilder();

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        event.getTextChannel().sendMessage(
            message.setColor(Color.CYAN).setTitle("Befehle")
                    .addField("-help", "``-help: zeigt diese Hilfe an``", false)
                    .addField("-add", "``-add [Falsche Schreibweise]: Fügt einen Vorschlag für eine falsche Schreibweise der Wörter 'Postillon', 'Postillleaks' und 'Faktillon' hinzu``", false)
                    .addField("-online", "``-online: Aktiviert / deaktiviert Direktnachrichten an den User, wenn der Wahrheitsgeneral seinen Status auf 'online' und 'offline' ändern``", false)
            .build())
        .queue();
        message.clear();

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
