package sh.twenty.octane.config

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.KSerializer
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ConfigManager(private val plugin: JavaPlugin) {

    private val yaml = Yaml(configuration = YamlConfiguration(strictMode = false))
    private val messagesFile = File(plugin.dataFolder, "messages.yml")
    private val configFile = File(plugin.dataFolder, "config.yml")

    var messages: MessagesConfig = MessagesConfig()
        private set
    var config: OctaneConfig = OctaneConfig()
        private set

    init { reload() }

    fun reload() {
        if (!messagesFile.exists()) plugin.saveResource("messages.yml", false)
        if (!configFile.exists()) plugin.saveResource("config.yml", false)
        messages = decode(messagesFile, MessagesConfig.serializer(), MessagesConfig())
        config   = decode(configFile,   OctaneConfig.serializer(),   OctaneConfig())
    }

    private fun <T> decode(file: File, serializer: KSerializer<T>, default: T): T = try {
        yaml.decodeFromString(serializer, file.readText())
    } catch (e: Exception) {
        plugin.logger.warning("Failed to parse ${file.name}: ${e.message}")
        default
    }
}
