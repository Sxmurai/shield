package wtf.shield.bot.command

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import wtf.shield.Launcher

abstract class SlashCommand(val name: String, val category: CommandCategory, val description: String) {
    abstract fun execute(event: SlashCommandInteractionEvent)

    fun isOwner(id: String): Boolean =
        Launcher.config.get("bot.owners", listOf<String>())!!.contains(id)

    fun replyEphemeral(event: SlashCommandInteractionEvent, content: String) {
        event.deferReply(true).setContent(content).queue()
    }

    fun replyEphemeral(event: SlashCommandInteractionEvent, content: String, action: () -> Unit) {
        event.deferReply(true).setContent(content).queue {
            action.invoke()
        }
    }
}

enum class CommandCategory {
    CORE,
    MODERATION,
    OWNER
}