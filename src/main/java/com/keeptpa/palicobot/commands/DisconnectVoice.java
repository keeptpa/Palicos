package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.Command;
import com.keeptpa.palicobot.audio.AudioController;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class DisconnectVoice extends Command {
    @Override
    public String[] getName() {
        return new String[]{"dc"};
    }

    @Override
    public String getDescription() {
        return "Disconnects the bot from voice channel";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        AudioController.getController(event.getChannel()).disconnect();
    }
}
