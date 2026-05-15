package sh.twenty.octane.listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sh.twenty.octane.Main;
import sh.twenty.octane.utils.ChatUtil;

public class PlayerJoinListener implements Listener {
    private final Main plugin;

    public PlayerJoinListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        String rawMessage = plugin.getConfigManager().getMessages().getString("join-message", "&7Welcome to the server, &b%player%&7.");
        String finalMessage = rawMessage.replace("%player%", player.getName());

        player.sendMessage(ChatUtil.color(finalMessage));
        // healing and setting their food level to max when they join
        player.setHealth(20.0);
        player.setFoodLevel(20);
    }
}
