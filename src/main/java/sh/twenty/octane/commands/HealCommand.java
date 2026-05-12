package sh.twenty.octane.commands;

import org.bukkit.entity.Player;
import sh.twenty.octane.Main;
import sh.twenty.octane.utils.ChatUtil;

public class HealCommand extends PlayerCommand {

    public HealCommand(Main plugin) {
        super(plugin, "octane.heal");
    }

    @Override
    public boolean execute(Player player, String[] args) {

        if (args.length >= 1){


            if(!player.hasPermission("octane.heal.others")){
                String rawMessage = plugin.getConfigManager().getMessages().getString("no-permission", "&cNo permission!");
                player.sendMessage(ChatUtil.color(rawMessage));
                return false;
            }

            Player target = plugin.getServer().getPlayer(args[0]);

            if (target == null){
                sendPrefixedMessage(player, "&cCould not find player: " + args[0]);
                return false;
            }

            target.setHealth(20.0);

            sendPrefixedMessage(player, "&7" + target.getName() + " has been healed.");
            sendPrefixedMessage(target, "&7You were healed by " + player.getName() + ".");
            return true;
        }
        player.setHealth(20.0);
        sendPrefixedMessage(player, "&7You have been fully healed.");
        return true;
    }
}
