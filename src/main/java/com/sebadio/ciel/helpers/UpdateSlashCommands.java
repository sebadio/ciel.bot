package com.sebadio.ciel.helpers;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;

public class UpdateSlashCommands {

    public ArrayList<SlashCommandData> getCommands() {
        return commands;
    }

    private final ArrayList<SlashCommandData> commands;

    public UpdateSlashCommands() {
        this.commands = new ArrayList<>();
        SlashCommandData ping = Commands.slash("ping", "pong! also send the latency to the server");

        SlashCommandData avatar = Commands.slash("avatar", "I send you the avatar of the person you want.")
                .addOption(OptionType.USER, "user", "the user you want the avatar from", true);

        SlashCommandData invite = Commands.slash("invite", "I send you a link to invite me to another guild!");

        SlashCommandData userinfo = Commands.slash("userinfo", "I send you the info about a user")
                .addOption(OptionType.USER, "user", "the user you want the info from", true);

        SlashCommandData ban = Commands.slash("ban", "I ban the user you tell me to")
                .addOption(OptionType.USER, "user", "The user you want to ban", true)
                .addOption(OptionType.STRING, "reason", "The reason you want to ban them, not really important though.", false)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS));

        SlashCommandData unban = Commands.slash("unban", "I unban the user you tell me to")
                .addOption(OptionType.USER, "user", "The user you want to unban", true)
                .addOption(OptionType.STRING, "reason", "The reason you want to unban them, not really important though.", false)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS));

        SlashCommandData kick = Commands.slash("kick", "I kick the user you tell me to")
                .addOption(OptionType.USER, "user", "The user you want to kick", true)
                .addOption(OptionType.STRING, "reason", "The reason you want to kick them, not really important though.", false)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.KICK_MEMBERS));

        SlashCommandData purge = Commands.slash("purge", "I remove the quantity of messages you specify me to from existence")
                .addOption(OptionType.INTEGER, "quantity", "Quantity of messages to erase", true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MESSAGE_MANAGE));

        commands.add(ping);
        commands.add(avatar);
        commands.add(invite);
        commands.add(userinfo);
        commands.add(ban);
        commands.add(unban);
        commands.add(kick);
        commands.add(purge);
    }


}
