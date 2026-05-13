package sh.twenty.octane.commands;

import org.bukkit.GameMode;
import sh.twenty.octane.Main;
import org.bukkit.entity.Player;
import sh.twenty.octane.utils.ChatUtil;


public class GmspCommand extends PlayerCommand {
    public GmspCommand(Main plugin) {super(plugin, "octane.gmsp"); }

    @Override
    public boolean execute(Player player, String[] args){
        if(args.length >= 1){
            if(!player.hasPermission("octane.gmsp.others")){
                String noPerm = plugin.getConfigManager().getMessages().getString("no-permission", "&cNo permission!");
                player.sendMessage(ChatUtil.color(noPerm));
                return false;
            }

            Player target = plugin.getServer().getPlayerExact(args[0]);

            if(target==null){
                String notFound = plugin.getConfigManager().getMessages().getString("player-not-found", "&cCould not find player: %player%");
                sendPrefixedMessage(player, notFound.replace("%player%", args[0]));
                return true;
            }

            target.setGameMode(GameMode.SPECTATOR);

            String successOther = plugin.getConfigManager().getMessages().getString("gmsp-success-other", "&7%player%'s gamemode was set to Spectator.");
            sendPrefixedMessage(player, successOther.replace("%player%", target.getName()));

            String successTarget = plugin.getConfigManager().getMessages().getString("gmsp-success-target", "&7%player%'s gamemode was set to Spectator.");
            sendPrefixedMessage(player, successTarget.replace("%player%", target.getName()));
            return true;
        }
        player.setGameMode(GameMode.SPECTATOR);

        String successSelf = plugin.getConfigManager().getMessages().getString("gmsp-success-self", "&7Your gamemode was set to Spectator");
        sendPrefixedMessage(player, successSelf);
        return true;
    }
}
