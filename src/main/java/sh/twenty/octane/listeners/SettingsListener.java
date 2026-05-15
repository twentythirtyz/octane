package sh.twenty.octane.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import sh.twenty.octane.Main;
import sh.twenty.octane.utils.ChatUtil;
import sh.twenty.octane.utils.SettingsMenuHolder;

public class SettingsListener implements Listener {

    private final Main plugin;

    public SettingsListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSettingsClick(InventoryClickEvent event) {


        if (event.getView().getTopInventory().getHolder() == null) return;
        if (!(event.getView().getTopInventory().getHolder() instanceof SettingsMenuHolder)) {
            return;
        }


        event.setCancelled(true);


        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;


        Player player = (Player) event.getWhoClicked();
        World world = player.getWorld();
        int clickedSlot = event.getRawSlot();



        int pvpSlot = plugin.getConfig().getInt("menus.settings.items.pvp.slot", 11);
        int timeSlot = plugin.getConfig().getInt("menus.settings.items.time.slot", 13);
        int weatherSlot = plugin.getConfig().getInt("menus.settings.items.weather.slot", 15);


        if (clickedSlot == pvpSlot) {
            boolean currentPvp = world.getPVP();
            world.setPVP(!currentPvp);
            player.sendMessage(ChatUtil.color("&8[&bOctane&8] &7PvP is now " + (!currentPvp ? "&aEnabled" : "&cDisabled")));

        } else if (clickedSlot == timeSlot) {
            boolean isDay = world.getTime() < 13000;
            world.setTime(isDay ? 13000 : 1000);
            player.sendMessage(ChatUtil.color("&8[&bOctane&8] &7Time set to " + (isDay ? "&9Night" : "&eDay") + "&7."));

        } else if (clickedSlot == weatherSlot) {
            boolean isRaining = world.hasStorm();
            world.setStorm(!isRaining);
            world.setThundering(!isRaining);
            player.sendMessage(ChatUtil.color("&8[&bOctane&8] &7Weather is now " + (!isRaining ? "&9Raining" : "&6Clear")));
        }

        // 7. Instantly refresh the GUI so the items update their colors
        player.performCommand("settings");
    }
}
