package wtf.shield.bot.command

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import wtf.shield.Launcher
import wtf.shield.util.ReflectionUtil

class CommandManager(pkg: String) {
    val commands = mutableMapOf<String, SlashCommand>()
    val commandList = mutableListOf<SlashCommand>()

    init {
        ReflectionUtil.classesFrom(pkg, SlashCommand::class).forEach {
            runCatching {
                val slashCommand = it.constructors.first().call() as SlashCommand

                commands[slashCommand.name] = slashCommand
                commandList += slashCommand
            }
        }

        Launcher.logger.info("Loaded ${commands.size} commands")
    }

    fun onSlashCommand(event: SlashCommandInteractionEvent) {
        val command = commands[event.name.lowercase()] ?: return
        command.execute(event)
    }
}