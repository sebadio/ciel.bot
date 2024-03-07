package com.sebadio.ciel.listeners;

import com.sebadio.ciel.commands.misc.Avatar;
import com.sebadio.ciel.commands.misc.Invite;
import com.sebadio.ciel.commands.misc.Ping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("Bot Ready!");
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;
        if(!event.isFromGuild()) return;
        String msg = event.getMessage().getContentRaw();

        if (msg.startsWith("!ping")){
            new Ping(event);
        } else if (msg.startsWith("!avatar")) {
            new Avatar(event);
        } else if(msg.startsWith("!invite")){
            new Invite(event);
        }

    }
}
