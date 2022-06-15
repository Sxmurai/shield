package wtf.shield.bot.listeners

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import wtf.shield.bot.Shield

class SlashCommandListener : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        Shield.instance.commandManager.onSlashCommand(event)
    }
}