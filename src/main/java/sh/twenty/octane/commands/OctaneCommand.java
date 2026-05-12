package sh.twenty.octane.commands;

import org.bukkit.entity.Player;
import sh.twenty.octane.Main;

public class OctaneCommand extends PlayerCommand {
    public OctaneCommand(Main plugin){
        super(plugin, "octane.admin");
    }
    @Override
    public boolean execute(Player player, String[] args){
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")){
            plugin.getConfigManager().reloadConfigs();

            sendPrefixedMessage(player, "&7The configuration has been reloaded.");
            return true;
        }

        sendPrefixedMessage(player, "&7Usage: /octane reload");
        return true;
    }
}
