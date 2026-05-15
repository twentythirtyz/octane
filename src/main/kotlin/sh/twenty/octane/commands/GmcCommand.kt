package sh.twenty.octane.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.GameMode
import org.bukkit.entity.Player
import sh.twenty.octane.OctanePlugin

class GmcCommand(private val plugin: OctanePlugin) {
    fun register() = commandAPICommand("gmc") {
        withPermission("octane.gmc")
        playerArgument("target", optional = true)
        playerExecutor { player, args ->
            val msgs = plugin.configManager.messages
            val target = args["target"] as? Player

            if (target != null && !player.hasPermission("octane.gmc.others")) {
                player.sendRichMessage(msgs.prefix + msgs.noPermission)
                return@playerExecutor
            }

            val finalTarget = target ?: player
            finalTarget.gameMode = GameMode.CREATIVE

            if (target != null) {
                player.sendRichMessage(msgs.prefix + msgs.gmcOther.replace("%player%", finalTarget.name))
                finalTarget.sendRichMessage(msgs.prefix + msgs.gmcTarget.replace("%sender%", player.name))
            } else {
                player.sendRichMessage(msgs.prefix + msgs.gmcSelf)
            }
        }
    }
}
