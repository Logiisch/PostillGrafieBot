package core;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.exceptions.PermissionException;

public class RoleManager {

    public static void addRole(Member member, Guild guild, Role role) {
        if (member == null || guild == null || role == null)
            return;
        try {
            guild.getController().addSingleRoleToMember(member, role).complete();
        } catch (PermissionException e) {
            e.printStackTrace();
        }
    }
}
