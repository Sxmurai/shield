package wtf.shield.bot.listeners

import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import wtf.shield.bot.Shield

class GuildCreateEvent : ListenerAdapter() {
    override fun onGuildJoin(event: GuildJoinEvent) {
        println("hi")

        Shield.instance.commandManager.commands.forEach { (name, command) ->
            event.guild.upsertCommand(name, command.description)
        }
        event.guild.updateCommands()

        event.guild.retrieveCommands().queue { it ->
            it.forEach { println(it.name) }
        }
    }
}