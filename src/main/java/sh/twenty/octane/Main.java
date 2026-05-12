package sh.twenty.octane;

import org.bukkit.plugin.java.JavaPlugin;
import sh.twenty.octane.commands.FeedCommand;
import sh.twenty.octane.commands.HealCommand;
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
        //registerListeners();
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
    }

//    private void registerListeners() {
    // Logic for event listeners
//    }
}
