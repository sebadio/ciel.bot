package com.sebadio.ciel.commands.misc;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import org.jetbrains.annotations.NotNull;

public class Ping {

    public Ping(@NotNull MessageReceivedEvent event){
        long timeSent = System.currentTimeMillis();
        Message message = event.getMessage();

        MessageCreateAction reply = message.reply("pong!");
        reply.queue((messageResponse) -> {
            long timeReceived = System.currentTimeMillis();
            long ping = timeReceived - timeSent;
            messageResponse.editMessageFormat("pong! `%s ms`", ping).queue();
        });
    }

}
