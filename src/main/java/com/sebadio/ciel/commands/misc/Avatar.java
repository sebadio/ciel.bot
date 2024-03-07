package com.sebadio.ciel.commands.misc;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Avatar {

    public Avatar(@NotNull MessageReceivedEvent event){
        User user = event.getAuthor();
        String avatar = user.getAvatarUrl();

        if(avatar == null || avatar.isEmpty()){
            event.getMessage().reply("Couldn't get the user's avatar.").queue();
            return;
        }
        avatar = avatar.concat("?size=2048");

        EmbedBuilder mb = new EmbedBuilder();
        mb.setFooter("ciel.bot");
        mb.setImage(avatar);
        mb.setTitle(String.format("%s's avatar", user.getName()));
        mb.setColor(new Color(122, 211, 214));

        event.getMessage().replyEmbeds(mb.build()).queue();
    }
}
