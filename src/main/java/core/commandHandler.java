package core;

import commands.Command;

import java.io.IOException;
import java.util.HashMap;

public class commandHandler {
    public static final commandParser parser = new commandParser();
    public static HashMap<String, Command> commands =new HashMap<>();

    public static void handleCommand(commandParser.commandContainer cmd) throws IOException {

    if (commands.containsKey(cmd.invoke)) {
    boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);
    if (!safe) {
        commands.get(cmd.invoke).action(cmd.args, cmd.event);
        commands.get(cmd.invoke).executed(safe, cmd.event);
    } else {
        commands.get(cmd.invoke).executed(safe, cmd.event);
    }
    } else {
        System.out.println("Der Befehl konnte nicht gefunden werden!");
    }
    }
}
