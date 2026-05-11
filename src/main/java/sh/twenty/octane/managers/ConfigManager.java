package sh.twenty.octane.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import sh.twenty.octane.Main;

import java.io.File;

public class ConfigManager {
    private final Main plugin;
    private FileConfiguration messagesConfig;
    private File messagesFile;

    public ConfigManager(Main plugin){
        this.plugin = plugin;
        setupConfigs();
    }
    public void setupConfigs(){
        plugin.saveDefaultConfig();

        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()){
            plugin.saveResource("messages.yml", false);
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
    public FileConfiguration getMessages(){
        return messagesConfig;
    }
}
