package com.sebadio.ciel.helpers;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommandUserReason {

    public Message message;
    public UserSnowflake userSnowflake;
    public Guild guild;
    public String reason;

    public CommandUserReason(){}

    public CommandUserReason(@NotNull MessageReceivedEvent event){
        Message message = event.getMessage();
        String msgContent = message.getContentRaw();

        Pattern pattern = Pattern.compile("!(\\w+) (?:<@!?)?(\\d{18})>?(?: (.*))?");
        Matcher matcher = pattern.matcher(msgContent);

        if (!matcher.find()) {
            message.reply("You need to provide an user id to use this command.").queue();
            return;
        }

        String userId = matcher.group(2);

        this.reason = matcher.group(3) != null ? matcher.group(2) : "No reason was provided";
        this.userSnowflake = UserSnowflake.fromId(userId);
        this.guild = event.getGuild();
        this.message = message;
    }

    public CommandUserReason(@NotNull SlashCommandInteractionEvent event) {
        this.guild = event.getGuild();
        String userId = event.getOption("user").getAsUser().getId();
        this.userSnowflake = UserSnowflake.fromId(userId);
        this.reason = event.getOption("reason") != null ? event.getOption("reason").getAsString() : "No reason provided";
    }

}
