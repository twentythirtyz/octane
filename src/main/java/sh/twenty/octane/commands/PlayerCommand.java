package sh.twenty.octane.commands;

// IMPORTANT: Double-check that these exact imports are the ones being used!
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

    // Notice we removed the @NotNull annotations here to prevent compiler mismatches
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

        execute(player, args);
        return true;
    }

    public abstract void execute(Player player, String[] args);

    protected void sendPrefixedMessage(Player player, String message) {
        String prefix = plugin.getConfigManager().getMessages().getString("prefix", "&8[&bOctane&8] ");
        player.sendMessage(ChatUtil.color(prefix + message));
    }
}
