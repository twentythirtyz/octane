package sh.twenty.octane.commands;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import sh.twenty.octane.utils.ChatUtil;
import sh.twenty.octane.Main;

public class GmcCommand extends PlayerCommand {
    public GmcCommand(Main plugin) {super(plugin, "octane.gmc"); }

    @Override
    public boolean execute(Player player, String[] args){
        if (args.length >= 1){

                    if(!player.hasPermission("octane.gmc.others")){
                String rawMessage = plugin.getConfigManager().getMessages().getString("no-permission", "&cNo permission!");
                player.sendMessage(ChatUtil.color(rawMessage));
                return false;
            }

            Player target = plugin.getServer().getPlayerExact(args[0]);

            if (target == null){
                sendPrefixedMessage(player, "&cCould not find player: " + args[0]);
                return false;
            }

            target.setGameMode(GameMode.CREATIVE);

            sendPrefixedMessage(player, "&7" + target.getName() + " had his gamemode set to Creative.");
            sendPrefixedMessage(target, "&7Your gamemode was set to Creative by " + player.getName() + ".");
            return true;
        }
        player.setGameMode(GameMode.CREATIVE);
        sendPrefixedMessage(player, "&7Your gamemode was set to Creative.");
        return true;
    }
}
