package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.BotState;
import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import com.keeptpa.palicobot.Configuer;
import com.keeptpa.palicobot.audio.AudioController;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Pause extends Command {

    @Override
    public String[] getName() {
        return new String[]{"p", "pause"};
    }

    @Override
    public String getDescription() {
        return "Pause the bot";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        AudioController.getController(event.getChannel()).togglePause();
        Chatter.Speak(event.getChannel(), AudioController.getController(event.getChannel()).isPaused() ? Configuer.localize("Music_Paused") : Configuer.localize("Music_Resume"));
    }
}
