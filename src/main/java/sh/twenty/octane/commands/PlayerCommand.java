package sh.twenty.octane.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sh.twenty.octane.Main;
import sh.twenty.octane.utils.ChatUtil;

public abstract class PlayerCommand implements CommandExecutor {

    protected final Main plugin;
    private final String permission;

    public PlayerCommand(Main plugin, String permission) {
        this.plugin = plugin;
        this.permission = permission;
    }

    public void register(String commandName) {
        org.bukkit.command.PluginCommand cmd = plugin.getCommand(commandName);
        if (cmd != null){
            cmd.setExecutor(this);
        } else {
            plugin.getLogger().warning("Failed to register the command: " + commandName);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatUtil.color("&cOnly players can run this command!"));
            return true;
        }

        if (!player.hasPermission(permission)) {
            String rawMessage = plugin.getConfigManager().getMessages().getString("no-permission", "&cNo permission!");
            player.sendMessage(ChatUtil.color(rawMessage));
            return true;
        }

        return execute(player, args);
    }

    public abstract boolean execute(Player player, String[] args);

    protected void sendPrefixedMessage(Player player, String message) {
        String prefix = plugin.getConfigManager().getMessages().getString("prefix", "&8[&bOctane&8] ");
        player.sendMessage(ChatUtil.color(prefix + message));
    }
}
