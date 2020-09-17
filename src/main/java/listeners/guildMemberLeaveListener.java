package listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class guildMemberLeaveListener extends ListenerAdapter {

    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {

        event.getGuild().getTextChannelById(446227161737723905L).sendMessage(
                new EmbedBuilder().setColor(Color.RED)
                        .setDescription("Der Verräter " + event.getUser().getName() + " hat die Timmy(9)-Befreiungsarmee verlassen!")
                        .build()
        ).queue();

    }

}
