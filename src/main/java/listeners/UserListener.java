package listeners;

import core.RoleManager;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.STATIC;

public class UserListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        RoleManager.addRole(event.getMember(), event.getGuild(), event.getGuild().getRoleById(STATIC.ROLE.LUEGENBARON));
    }
}
