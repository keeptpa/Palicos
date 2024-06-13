package com.keeptpa.palicobot;

import com.keeptpa.palicobot.commands.*;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BotState.instance.poemAPI = args[1];
        RegisterCommands();
        JDABuilder.createLight(args[0], EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT))
                .addEventListeners(new MessageReceiveListener())
                .build();
    }

    public static void RegisterCommands(){
        CommandDealer.instance.registerCommand(new GetPoem());
        CommandDealer.instance.registerCommand(new Ping());
        CommandDealer.instance.registerCommand(new AutoPraise());
        CommandDealer.instance.registerCommand(new Mockery());
        CommandDealer.instance.registerCommand(new MCServer());
        CommandDealer.instance.registerCommand(new Help());
    }
}

