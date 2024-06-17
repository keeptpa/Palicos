package com.keeptpa.palicobot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandDealer {
    public static CommandDealer instance = new CommandDealer();
    HashMap<String, Command> commandList = new HashMap<>();
    ArrayList<Command> commands = new ArrayList<>();
    public void registerCommand(Command command) {
        for (String name : command.getName()) {
            commandList.put(name, command);
        }
        commands.add(command);
    }

    public ArrayList<Command> getCommands(){
        return commands;
    }

    public void commandDistribution(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay();
        String author = event.getAuthor().getName();
        String[] args = message.replaceFirst("!", "").split(" ");
        String command = args[0];

        if (commandList.containsKey(command)) {
            Command command1 = commandList.get(command);
            command1.Execute(event, args);
        } else {
            Chatter.Speak(event.getChannel(), Configuer.localize("Unknown_Command"));
        }
    }
}
