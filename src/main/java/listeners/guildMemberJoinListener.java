package listeners;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.STATIC;

public class guildMemberJoinListener extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event)
    {

        String[] welcome = {
                "Willkommen, " + event.getUser().getAsMention() + ", bei den Aufklärern der Lügen des Postillons.",
                "" + event.getUser().getAsMention() + " hat auch genug von den Lügen des Postillons. Willkommen!",
                "" + event.getUser().getAsMention() + " ist auf dem besten Weg, den Lügen des Postillons zu entkommen. Willkommen!",
                "" + event.getUser().getAsMention() + " lässt sich ab sofort vom Postillon nicht mehr hinter's Licht führen."
        };

        event.getGuild().getTextChannelById(STATIC.CHANNEL.SMALLTALK).sendMessage(welcome[(int) (Math.random() * welcome.length)] + "\nSchau dich im <#" + STATIC.CHANNEL.WILLKOMMEN + "> Channel um :blush:").queue(); // ID: 446227161737723905

        Role neueRolle = event.getGuild().getRoleById(446377795900145665L);
        Member member = event.getMember();

        member.getGuild().getController().addRolesToMember(member, neueRolle).queue();
    }

}
