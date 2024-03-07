package com.sebadio.ciel.commands.admin;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ban {

    public Ban(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        String msgContent = message.getContentRaw();

        Pattern pattern = Pattern.compile("!ban (?:<@!?)?(\\d+)>?(?: (.*))?");
        Matcher matcher = pattern.matcher(msgContent);

        if (!matcher.find()) {
            message.reply("You need to provide an user id for me to ban someone.").queue();
            return;
        }

        String userId = matcher.group(1);
        String reason = matcher.group(2) != null ? matcher.group(2) : "No reason was provided";
        UserSnowflake userSnowflake = UserSnowflake.fromId(userId);
        Guild guild = event.getGuild();

        guild.ban(userSnowflake, 0, TimeUnit.DAYS)
                .reason(reason)
                .queue(
                        s -> message.reply("User banned successfully!").queue(),
                        f -> message.reply("User could not be banned").queue()
                );
    }

}
