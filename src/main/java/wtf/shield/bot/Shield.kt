package wtf.shield.bot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import wtf.shield.Launcher
import wtf.shield.bot.command.CommandManager
import wtf.shield.util.ReflectionUtil

class Shield(token: String) {
    val commandManager: CommandManager

    init {
        Launcher.logger.info("Loading shield...")

        val builder = JDABuilder.createLight(token)
            .setMemberCachePolicy(MemberCachePolicy.DEFAULT)
            .setEnabledIntents(GatewayIntent.GUILD_BANS, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_INVITES)
            .setActivity(Activity.watching("over your discord server"))
            .setStatus(OnlineStatus.DO_NOT_DISTURB)

        ReflectionUtil.classesFrom("wtf.shield.bot.listeners", ListenerAdapter::class).forEach {
            runCatching {
                val adapter = it.constructors.first().call() as ListenerAdapter
                builder.addEventListeners(adapter)
            }
        }

        commandManager = CommandManager("wtf.shield.bot.command.impl")
        bot = builder.build()

        instance = this
    }

    companion object {
        lateinit var bot: JDA
        lateinit var instance: Shield
    }
}