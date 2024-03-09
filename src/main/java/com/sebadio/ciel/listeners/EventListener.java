package com.sebadio.ciel.listeners;

import com.sebadio.ciel.commands.admin.Ban;
import com.sebadio.ciel.commands.admin.Kick;
import com.sebadio.ciel.commands.admin.Unban;
import com.sebadio.ciel.commands.misc.Avatar;
import com.sebadio.ciel.commands.misc.Invite;
import com.sebadio.ciel.commands.misc.Ping;
import com.sebadio.ciel.commands.misc.UserInfo;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
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

        if (msg.startsWith("!ping"))new Ping(event);
        else if (msg.startsWith("!avatar")) new Avatar(event);
        else if(msg.startsWith("!invite"))new Invite(event);
        else if(msg.startsWith("!ban"))new Ban(event);
        else if (msg.startsWith("!unban")) new Unban(event);
        else if(msg.startsWith("!userinfo"))new UserInfo(event);
        else if(msg.startsWith("!kick")) new Kick(event);

    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getUser().isBot()) return;
        if(event.isFromGuild()) return;

        String command = event.getName();
        
    }
}
