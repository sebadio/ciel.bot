package com.sebadio.ciel.helpers;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public abstract class CommandUserReason extends CommandUser {

    public String reason;

    public CommandUserReason(){}

    public CommandUserReason(@NotNull MessageReceivedEvent event){
        super(event);
        if(this.matcher == null || !this.matcher.find()) return;
        this.reason = this.matcher.group(3) != null ? this.matcher.group(2) : "No reason was provided";
    }

    public CommandUserReason(@NotNull SlashCommandInteractionEvent event) {
        super(event);
        this.reason = event.getOption("reason") != null ? event.getOption("reason").getAsString() : "No reason provided";
    }

}
