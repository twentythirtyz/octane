package sh.twenty.octane.commands;

import org.bukkit.GameMode;
import sh.twenty.octane.Main;
import org.bukkit.entity.Player;
import sh.twenty.octane.utils.ChatUtil;

public class GmsCommand extends PlayerCommand{
    public GmsCommand(Main plugin) {super(plugin, "octane.gms"); }


    @Override
    public boolean execute(Player player, String[] args){
        if (args.length >= 1){
            if(!player.hasPermission("octane.gms.others")){
                String noPerm = plugin.getConfigManager().getMessages().getString("no-permission", "&cNo permission!");
                player.sendMessage(ChatUtil.color(noPerm));
                return false;
            }

            Player target = plugin.getServer().getPlayerExact(args[0]);

            if(target == null){
                String notFound = plugin.getConfigManager().getMessages().getString("player-not-found", "&cCould not find player: %player%");
                sendPrefixedMessage(player, notFound.replace("%player%", args[0]));
                return true;
            }

            target.setGameMode(GameMode.SURVIVAL);

            String successOther = plugin.getConfigManager().getMessages().getString("gms-success-other", "&7%player%'s gamemode was set to Survival.");
            sendPrefixedMessage(player, successOther.replace("%player%", target.getName()));

            String successTarget = plugin.getConfigManager().getMessages().getString("gms-success-target", "&7%player%'s gamemode was set to Survival.");
            sendPrefixedMessage(player, successTarget.replace("%player%", target.getName()));
            return true;
        }
        player.setGameMode(GameMode.SURVIVAL);


        String successSelf = plugin.getConfigManager().getMessages().getString("gms-success-self", "&7Your gamemode was set to Survival");
        sendPrefixedMessage(player, successSelf);
        return true;
    }
}
