package wtf.shield.bot.command.impl.owner

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import wtf.shield.bot.command.CommandCategory
import wtf.shield.bot.command.SlashCommand

class Shutdown : SlashCommand("shutdown", CommandCategory.OWNER, "does what it says lol") {
    override fun execute(event: SlashCommandInteractionEvent) {
        if (!isOwner(event.user.id)) {
            replyEphemeral(event, "no xd")
            return
        }

        replyEphemeral(event, "ok bye :wave:") {
            event.jda.shutdownNow()
        }
    }
}