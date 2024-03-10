package com.sebadio.ciel.commands.misc;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class UserInfo {

    public UserInfo(@NotNull MessageReceivedEvent event){
        Message message = event.getMessage();
        Mentions mentions = message.getMentions();

        if(mentions.getMembers().isEmpty()){
            message.reply("Provide a user as a target.").queue();
            return;
        }

        User user = mentions.getUsers().get(0);
        Member member = mentions.getMembers().get(0);

        MessageEmbed embed = createEmbed(user, member);

        message.replyEmbeds(embed).queue();
    }

    public UserInfo(@NotNull SlashCommandInteractionEvent event) {
        Member member = event.getOption("user").getAsMember();
        User user = event.getOption("user").getAsUser();

        MessageEmbed embed = createEmbed(user, member);

        event.replyEmbeds(embed).queue();
    }

    @NotNull
    private MessageEmbed createEmbed(@NotNull User user, @NotNull Member member){

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(String.format("%s's information", user.getName()));
        embed.setThumbnail(user.getAvatarUrl());
        embed.addField("Username", user.getName(), true);
        if(member.getNickname()!= null && !member.getNickname().isEmpty()){
            embed.addField("Nickname", member.getNickname(), true);
        }
        embed.addField("Is Bot?", user.isBot() ? "Yes" : "No", true);
        embed.addField("User ID", user.getId(), false);
        embed.addField("Account created", DateTimeFormatter.ofPattern("dd-MM-yyyy").format(user.getTimeCreated()), true);
        embed.addField("Join Date", DateTimeFormatter.ofPattern("dd-MM-yyyy").format(member.getTimeJoined()), true);
        String roles = Arrays.toString(member.getRoles()
                .stream()
                .map(Role::getName).toArray());
        embed.addField("Status", member.getOnlineStatus().name(), false);
        embed.addField("Roles", roles, false);
        embed.setColor(new Color(50, 157, 168));

        return embed.build();
    }
}
