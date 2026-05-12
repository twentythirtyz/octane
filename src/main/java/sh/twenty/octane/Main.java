package sh.twenty.octane;

import org.bukkit.plugin.java.JavaPlugin;
import sh.twenty.octane.commands.*;
import sh.twenty.octane.listeners.PlayerJoinListener;
import sh.twenty.octane.managers.ConfigManager;
import org.slf4j.Logger;

public final class Main extends JavaPlugin {

    private Logger logger;
    private ConfigManager configManager;

    @Override
    public void onEnable() {

        this.logger = getSLF4JLogger();
        final var meta = getPluginMeta();
        logger.info("Initializing {} v{}", meta.getName(), meta.getVersion());

        this.configManager = new ConfigManager(this);

        // Example usages
        boolean isFeatureEnabled = getConfig().getBoolean("some-setting", true);
        String prefix = configManager.getMessages().getString("prefix", "[Octane]");

        // 3. Register everything else
        registerCommands();
        registerListeners();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public void onDisable() {

        if (logger != null) {
            logger.info("Disabling Octane...");
        }
    }

    private void registerCommands(){
        getCommand("feed").setExecutor(new FeedCommand(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("octane").setExecutor(new OctaneCommand(this));
        getCommand("gmc").setExecutor(new GmcCommand(this));
        getCommand("gms").setExecutor(new GmsCommand(this));
    }


    private void registerListeners(){
        // we get the bukkit pluginmanager
        org.bukkit.plugin.PluginManager pm = getServer().getPluginManager();
        // this registers the playerjoinlistener how i know
        pm.registerEvents(new PlayerJoinListener(this), this);
    }
}
