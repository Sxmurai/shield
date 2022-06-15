package wtf.shield.bot.listeners

import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.Commands
import wtf.shield.Launcher
import wtf.shield.bot.Shield

class ReadyListener : ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        val user = event.jda.selfUser
        Launcher.logger.info("Logged into account ${user.name}#${user.discriminator} (${user.id})")

        event.jda.guilds.forEach {
            it.updateCommands().let { action ->
                Shield.instance.commandManager.commands.forEach { (name, command) ->
                    action.addCommands(Commands.slash(name, command.description))
                }

                action.queue()
            }

            Launcher.logger.info("Updating slash commands in guild ${it.name} (${it.id})")
        }
    }
}