package com.keeptpa.palicobot.commands;
import com.keeptpa.palicobot.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class GetPoem extends Command {

    @Override
    public String[] getName() {
        return new String[]{"get-poem", "gp", "念诗"};
    }

    @Override
    public String getDescription() {
        return "Get a random poem";
    }

    @Override
    public void Execute(MessageReceivedEvent event, String[] args) {
        String reply = HttpClient.get("https://api.apileague.com/retrieve-random-poem?api-key=" + BotConst.POEM_API, null);
        JSONObject obj = (JSONObject) JSONValue.parse(reply);
        reply = obj.get("poem").toString();
        Chatter.Speak(event.getChannel(), reply);
    }
}
