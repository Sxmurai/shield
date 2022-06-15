package wtf.shield

import org.slf4j.LoggerFactory
import wtf.shield.bot.Shield

object Launcher {
    val logger = LoggerFactory.getLogger("Shield")
    val config = Configuration("shield.cfg")

    @JvmStatic
    fun main(args: Array<String>) {
        runCatching {
            val token = config.get<String>("bot.token", null) ?: throw Exception("No token provided")
            Shield(token)
        }
    }
}