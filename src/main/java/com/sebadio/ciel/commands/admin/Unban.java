package com.sebadio.ciel.commands.admin;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unban {

    public Unban(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        Pattern pattern = Pattern.compile("!unban (?:<@!?)?(\\d+)>?(?: (.*))?");
        Matcher matcher = pattern.matcher(message.getContentRaw());

        if (!matcher.find()) {
            message.reply("You need to provide a user id for me to unban them.").queue();
            return;
        }


        String userId = matcher.group(1);
        UserSnowflake userSnowflake = UserSnowflake.fromId(userId);
        Guild guild = event.getGuild();

        guild.unban(userSnowflake)
                .queue(
                        s -> event.getMessage().reply("Unbanned successfully!").queue(),
                        f -> event.getMessage().reply("Failed to unban user").queue()
                );
    }

}
