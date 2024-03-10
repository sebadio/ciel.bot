package com.sebadio.ciel.listeners;

import com.sebadio.ciel.commands.admin.Ban;
import com.sebadio.ciel.commands.admin.Kick;
import com.sebadio.ciel.commands.admin.Unban;
import com.sebadio.ciel.commands.misc.Avatar;
import com.sebadio.ciel.commands.misc.Invite;
import com.sebadio.ciel.commands.misc.Ping;
import com.sebadio.ciel.commands.misc.UserInfo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class PrefixEventListener extends ListenerAdapter {

    private final Map<String, Consumer<MessageReceivedEvent>> commands;

    public PrefixEventListener() {
        this.commands = new HashMap<>();
        commands.put("!ping", Ping::new);
        commands.put("!avatar", Avatar::new);
        commands.put("!invite", Invite::new);
        commands.put("!ban", Ban::new);
        commands.put("!unban", Unban::new);
        commands.put("!userinfo", UserInfo::new);
        commands.put("!kick", Kick::new);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        if (!event.isFromGuild()) return;

        String command = event.getMessage().getContentRaw().split(" ")[0];
        Consumer<MessageReceivedEvent> action = commands.get(command);
        if (action != null) {
            action.accept(event);
        }
    }

}
