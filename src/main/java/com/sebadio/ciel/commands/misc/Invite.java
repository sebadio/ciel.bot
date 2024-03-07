package com.sebadio.ciel.commands.misc;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

public class Invite {

    public Invite(@NotNull MessageReceivedEvent event){
        String inviteLink = String.format("https://discord.com/api/oauth2/authorize?client_id=%s&permissions=17740865203414&scope=bot", "1214983925626314832");
        event.getMessage()
                .reply("You can invite me by clicking the button")
                .addActionRow(
                        Button.link(inviteLink, "Invite Me!"))
                .queue();
    }

}
