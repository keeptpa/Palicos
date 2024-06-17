package com.keeptpa.palicobot.commands;

import com.keeptpa.palicobot.BotState;
import com.keeptpa.palicobot.Chatter;
import com.keeptpa.palicobot.Command;
import com.keeptpa.palicobot.Configuer;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;

public class RockPaper extends Command {
    @Override
    public String[] getName() {
        return new String[]{"rps"};
    }

    @Override
    public String getDescription() {
        return "Rock Paper Scissors or such game?";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        MessageCreateAction action = Chatter.SpeakWithoutQueue(event.getChannel(), Configuer.localize("Rock_Paper_Scissors"));
        action.setActionRow(
                Button.secondary("Rock" + event.getChannel().getId(), "Rock").withEmoji(Emoji.fromUnicode("✊")),
                Button.secondary("Paper" + event.getChannel().getId(), "Paper").withEmoji(Emoji.fromUnicode("✋")),
                Button.secondary("Scissors" + event.getChannel().getId(), "Scissors").withEmoji(Emoji.fromUnicode("✌️"))
        );
        action.queue();

        int botSelect = (int) (Math.random() * 2 + 1);
        BotState.getBotState(event.getChannel()).setBotRockPaperScissors(botSelect);
    }

    public static void onButtonClick(ButtonInteractionEvent event) {

        if(BotState.getBotState(event.getChannel()).getBotRockPaperScissors() == -1) {
            return;
        }

        int botSelect = BotState.getBotState(event.getChannel()).getBotRockPaperScissors();
        String botSelectID = "";
        //rock = 1, paper = 2, scissors = 3
        String rockID = "Rock" + event.getChannel().getId();
        String paperID = "Paper" + event.getChannel().getId();
        String scissorsID = "Scissors" + event.getChannel().getId();

        switch (botSelect)
        {
            case 1:
                botSelectID = "Rock";
                break;
            case 2:
                botSelectID = "Paper"; ;
                break;
            case 3:
                botSelectID = "Scissors";
                break;
        }


        int playerSelect = 0;
        String playerClickID = event.getComponent().getLabel();
        if(playerClickID.equals("Rock")) playerSelect = 1;
        if(playerClickID.equals("Paper")) playerSelect = 2;
        if(playerClickID.equals("Scissors")) playerSelect = 3;

        Chatter.Speak(event.getChannel(), Configuer.localize("You_Picked") + " " +  Configuer.localize(playerClickID));
        Chatter.Speak(event.getChannel(), Configuer.localize("I_Picked") + Configuer.localize(botSelectID));
        if(playerSelect > botSelect && playerSelect - botSelect == 1 || playerSelect < botSelect && Math.abs(playerSelect - botSelect) == 2) {
            Chatter.Speak(event.getChannel(), Configuer.localize("You_Win"));
        }else if(playerSelect != botSelect) {
            Chatter.Speak(event.getChannel(), Configuer.localize("You_Lose"));
        }else{
            Chatter.Speak(event.getChannel(), Configuer.localize("Draw"));
        }
        event.editMessage(Configuer.localize("Rock_Paper_Scissors")).queue();
        BotState.getBotState(event.getChannel()).setBotRockPaperScissors(-1);
    }
}
