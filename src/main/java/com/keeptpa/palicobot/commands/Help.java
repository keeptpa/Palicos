package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import com.keeptpa.palicobot.CommandDealer;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends Command {
    @Override
    public String[] getName() {
        return new String[]{"help"};
    }

    @Override
    public String getDescription() {
        return "Get help";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        StringBuilder result = new StringBuilder();
        for (Command cmds : CommandDealer.instance.getCommands()) {
            StringBuilder command = new StringBuilder();
            for (String name : cmds.getName()) {
                command.append(name).append("/");
            }
            result.append(command).append(" - ").append(cmds.getDescription()).append("\n");
        }

        Chatter.speak(event.getChannel(), result.toString());
    }
}
