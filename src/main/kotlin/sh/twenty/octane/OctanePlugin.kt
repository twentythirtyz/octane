package sh.twenty.octane

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import org.bukkit.plugin.java.JavaPlugin
import sh.twenty.octane.commands.*
import sh.twenty.octane.config.ConfigManager
import sh.twenty.octane.gui.GuiListener
import sh.twenty.octane.listeners.PlayerJoinListener

class OctanePlugin : JavaPlugin() {

    lateinit var configManager: ConfigManager
        private set

    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this).verboseOutput(false))
    }

    override fun onEnable() {
        CommandAPI.onEnable()
        configManager = ConfigManager(this)
        registerCommands()
        registerListeners()
        logger.info("Octane v${pluginMeta.version} enabled.")
    }

    override fun onDisable() {
        CommandAPI.onDisable()
        logger.info("Octane disabled.")
    }

    private fun registerCommands() {
        HealCommand(this).register()
        FeedCommand(this).register()
        GmcCommand(this).register()
        GmsCommand(this).register()
        GmspCommand(this).register()
        GmaCommand(this).register()
        FlyCommand(this).register()
        FlySpeedCommand(this).register()
        OctaneCommand(this).register()
        SettingsCommand(this).register()
    }

    private fun registerListeners() {
        val pm = server.pluginManager
        pm.registerEvents(PlayerJoinListener(this), this)
        pm.registerEvents(GuiListener(), this)
    }
}
