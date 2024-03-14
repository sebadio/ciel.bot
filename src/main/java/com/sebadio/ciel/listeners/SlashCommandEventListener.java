package com.sebadio.ciel.listeners;

import com.sebadio.ciel.commands.admin.*;
import com.sebadio.ciel.commands.misc.Avatar;
import com.sebadio.ciel.commands.misc.Invite;
import com.sebadio.ciel.commands.misc.Ping;
import com.sebadio.ciel.commands.misc.UserInfo;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SlashCommandEventListener extends ListenerAdapter {

    private final Map<String, Consumer<SlashCommandInteractionEvent>> commands;

    public SlashCommandEventListener() {
        this.commands = new HashMap<>();
        commands.put("ping", Ping::new);
        commands.put("avatar", Avatar::new);
        commands.put("invite", Invite::new);
        commands.put("ban", Ban::new);
        commands.put("unban", Unban::new);
        commands.put("softban", Softban::new);
        commands.put("userinfo", UserInfo::new);
        commands.put("kick", Kick::new);
        commands.put("purge", Purge::new);
    }


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getUser().isBot()) return;
        if (!event.isFromGuild()) return;

        Consumer<SlashCommandInteractionEvent> command = commands.get(event.getName().toLowerCase());
        if (command != null) {
            command.accept(event);
        }
    }
}

