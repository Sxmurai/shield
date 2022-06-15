package wtf.shield

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import java.nio.file.Paths
import kotlin.io.path.isReadable

class Configuration(name: String) {
    private lateinit var config: Config

    init {
        val path = Paths.get(name)
        if (path.isReadable()) {
            config = ConfigFactory.load(ConfigFactory.parseFile(path.toFile()).resolve())
        } else {
            throw Exception("No config file named $name")
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(path: String, defaultValue: T? = null): T? {
        return config.getAnyRef(path) as T ?: defaultValue
    }
}