package sh.twenty.octane.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sh.twenty.octane.Main;
import sh.twenty.octane.utils.ChatUtil;
import sh.twenty.octane.utils.SettingsMenuHolder;

import java.util.List;

public class SettingsCommand extends PlayerCommand {

    public SettingsCommand(Main plugin) {
        super(plugin, "octane.admin");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        String title = plugin.getConfig().getString("menus.settings.title", "&8Server Settings");
        int size = plugin.getConfig().getInt("menus.settings.size", 27);

        Inventory menu = Bukkit.createInventory(new SettingsMenuHolder(), size, ChatUtil.color(title));
        World world = player.getWorld();


        int pvpSlot = plugin.getConfig().getInt("menus.settings.items.pvp.slot", 11);
        boolean pvpState = world.getPVP(); // true = enabled, false = disabled
        menu.setItem(pvpSlot, buildDynamicItem("menus.settings.items.pvp", pvpState));


        int timeSlot = plugin.getConfig().getInt("menus.settings.items.time.slot", 13);
        boolean timeState = world.getTime() < 13000; // true = day, false = night
        menu.setItem(timeSlot, buildDynamicItem("menus.settings.items.time", timeState));


        int weatherSlot = plugin.getConfig().getInt("menus.settings.items.weather.slot", 15);
        boolean weatherState = world.hasStorm(); // true = raining, false = clear
        menu.setItem(weatherSlot, buildDynamicItem("menus.settings.items.weather", weatherState));

        player.openInventory(menu);
        return true;
    }


    private ItemStack buildDynamicItem(String basePath, boolean currentState) {

        String statePath = currentState ? basePath + ".true-state" : basePath + ".false-state";


        String materialName = plugin.getConfig().getString(statePath + ".material", "STONE");
        String name = plugin.getConfig().getString(statePath + ".name", "&cMissing Name");
        List<String> loreList = plugin.getConfig().getStringList(statePath + ".lore");

        Material material = Material.matchMaterial(materialName);
        if (material == null) material = Material.BEDROCK;

        // Build the item
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.displayName(ChatUtil.color(name));
            meta.lore(loreList.stream().map(ChatUtil::color).toList());

            item.setItemMeta(meta);
        }
        return item;
    }
}
