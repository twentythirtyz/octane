package sh.twenty.octane.commands;

import org.bukkit.entity.Player;
import sh.twenty.octane.Main;
import sh.twenty.octane.utils.ChatUtil;


public class FeedCommand extends PlayerCommand {
    public FeedCommand(Main plugin) {
        super(plugin, "octane.feed");
    }

    @Override
    public boolean execute(Player player, String[] args){

        if (args.length >= 1) {
            if (!player.hasPermission("octane.feed.others")){
                String rawMessage = plugin.getConfigManager().getMessages().getString("no-permission", "&cNo permission!");
                player.sendMessage(ChatUtil.color(rawMessage));
                return false;
            }

            Player target = plugin.getServer().getPlayerExact(args[0]);

            if(target == null){
                sendPrefixedMessage(player, "&cCould not find player: " + args[0]);
                return true;
            }

            target.setFoodLevel(20);
            target.setSaturation(20f);

            sendPrefixedMessage(player, "&7You have sated " + target.getName() + "'s appetite.");
            sendPrefixedMessage(target, "&7You were appetite was sated by " + player.getName() + ".");
            return true;
        }



        player.setFoodLevel(20);
        player.setSaturation(20f);

        sendPrefixedMessage(player, "&7Your appetite has been sated.");
        return true;
    }
}
