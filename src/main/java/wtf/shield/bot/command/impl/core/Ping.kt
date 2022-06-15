package wtf.shield.bot.command.impl.core

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import wtf.shield.bot.command.CommandCategory
import wtf.shield.bot.command.SlashCommand

class Ping : SlashCommand("ping", CommandCategory.CORE, "Tells you the latency and shit") {
    override fun execute(event: SlashCommandInteractionEvent) {
        event.jda.restPing.queue {
            replyEphemeral(event, "ping pong\nws: ${event.jda.gatewayPing}\nrest: $it")
        }
    }
}