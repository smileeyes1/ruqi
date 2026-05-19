package com.ruqi.eduos.mesc;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private static final Map<String, AppCommand> commands = new HashMap<>();

    public static void register(AppCommand command) {
        commands.put(command.getName(), command);
    }

    public static AppCommand getCommand(String name) {
        return commands.get(name);
    }
}
