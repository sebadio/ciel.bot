package com.sebadio.ciel.commands.admin;

import com.sebadio.ciel.helpers.CommandUser;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.TimeUnit;

public class Softban extends CommandUser {

    private final String successMessage = "%s softbanned successfully!";
    private final String noPermissionsErrorMessage = "I don't have permissions to ban members in this server!";
    private final String banErrorMessage = "Failed to ban %s";
    private final String unbanErrorMessage = "Failed to unban %s";
    public Softban(MessageReceivedEvent event) {
        super(event);

        if(!guild.getSelfMember().hasPermission(Permission.BAN_MEMBERS)){
            event.getMessage().reply(noPermissionsErrorMessage).queue();
            return;
        }

        guild.ban(userSnowflake, 0, TimeUnit.DAYS).queue(
                success -> guild.unban(userSnowflake).queue(
                        unbanSuccess -> event.getMessage().replyFormat(successMessage, userSnowflake.getAsMention()).queue(),
                        unbanFailed -> event.getMessage().replyFormat(unbanErrorMessage, userSnowflake.getAsMention()).queue()
                ),
                fail -> event.getMessage().replyFormat(banErrorMessage, userSnowflake.getAsMention()).queue()
        );

    }

    public Softban(SlashCommandInteractionEvent event) {
        super(event);

        if(!guild.getSelfMember().hasPermission(Permission.BAN_MEMBERS)){
            event.reply(noPermissionsErrorMessage).queue();
            return;
        }

        guild.ban(userSnowflake, 0, TimeUnit.DAYS).queue(
                success -> guild.unban(userSnowflake).queue(
                        unbanSuccess -> event.replyFormat(successMessage, userSnowflake.getAsMention()).queue(),
                        unbanFailed -> event.replyFormat(unbanErrorMessage, userSnowflake.getAsMention()).queue()
                ),
                fail -> event.replyFormat(banErrorMessage, userSnowflake.getAsMention()).queue()
        );
    }

}
