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
                String rawMessage = plugin.getConfigManager().getMessages().getString("no-permission", "&cNo permission!");
                player.sendMessage(ChatUtil.color(rawMessage));
                return false;
            }

            Player target = plugin.getServer().getPlayerExact(args[0]);

            if(target == null){
                sendPrefixedMessage(player, "&cCould not find the player: " + args[0]);
                return true;
            }

            target.setGameMode(GameMode.SURVIVAL);

            sendPrefixedMessage(player, "&7" + target.getName() + " had his gamemode set to Survival.");
            sendPrefixedMessage(target, "&7Your gamemode was set to Survival by " + player.getName() + ".");
            return true;
        }
        player.setGameMode(GameMode.SURVIVAL);
        sendPrefixedMessage(player, "&7Your gamemode was set to Survival.");
        return true;
    }
}
