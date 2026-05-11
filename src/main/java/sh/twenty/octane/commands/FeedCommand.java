package sh.twenty.octane.commands;

import org.bukkit.entity.Player;
import sh.twenty.octane.Main;
import sh.twenty.octane.utils.ChatUtil;


public class FeedCommand extends PlayerCommand {
    public FeedCommand(Main plugin) {
        super(plugin, "octane.feed");
    }

    @Override
    public void execute(Player player, String[] args){

        if (args.length >= 1) {
            if (!player.hasPermission("octane.feed.others")){
                String rawMessage = plugin.getConfigManager().getMessages().getString("no-permission", "&cNo permission!");
                player.sendMessage(ChatUtil.color(rawMessage));
                return;
            }

            Player target = plugin.getServer().getPlayer(args[0]);

            if(target == null){
                sendPrefixedMessage(player, "&7Could not find player: " + args[0]);
                return;
            }

            target.setHealth(20.0);

            sendPrefixedMessage(player, "&7You were apetite was sated by " + player.getName() + ".");
            return;
        }



        player.setFoodLevel(20);
        player.setSaturation(20f);

        sendPrefixedMessage(player, "&7Your apetite has been sated.");
    }
}
