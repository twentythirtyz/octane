package sh.twenty.octane.commands;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import sh.twenty.octane.utils.ChatUtil;
import sh.twenty.octane.Main;

public class GmcCommand extends PlayerCommand {

    public GmcCommand(Main plugin) {
        super(plugin, "octane.gmc");
    }

    @Override
    public boolean execute(Player player, String[] args){
        if (args.length >= 1) {

            if (!player.hasPermission("octane.gmc.others")) {
                String noPerm = plugin.getConfigManager().getMessages().getString("no-permission", "&cNo permission!");
                player.sendMessage(ChatUtil.color(noPerm));
                return true;
            }

            Player target = plugin.getServer().getPlayerExact(args[0]);

            if (target == null) {
                String notFound = plugin.getConfigManager().getMessages().getString("player-not-found", "&cCould not find player: %player%");
                sendPrefixedMessage(player, notFound.replace("%player%", args[0]));
                return true;
            }

            target.setGameMode(GameMode.CREATIVE);


            String successOther = plugin.getConfigManager().getMessages().getString("gmc-success-other", "&7%player%'s gamemode was set to Creative.");
            sendPrefixedMessage(player, successOther.replace("%player%", target.getName()));

            String successTarget = plugin.getConfigManager().getMessages().getString("gmc-success-target", "&7Your gamemode was set to Creative by %sender%.");
            sendPrefixedMessage(target, successTarget.replace("%sender%", player.getName()));

            return true;
        }

        player.setGameMode(GameMode.CREATIVE);


        String successSelf = plugin.getConfigManager().getMessages().getString("gmc-success-self", "&7Your gamemode was set to Creative.");
        sendPrefixedMessage(player, successSelf);

        return true;
    }
}
