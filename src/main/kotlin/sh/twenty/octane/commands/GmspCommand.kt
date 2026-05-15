package sh.twenty.octane.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.GameMode
import org.bukkit.entity.Player
import sh.twenty.octane.OctanePlugin

class GmspCommand(private val plugin: OctanePlugin) {
    fun register() = commandAPICommand("gmsp") {
        withPermission("octane.gmsp")
        playerArgument("target", optional = true)
        playerExecutor { player, args ->
            val msgs = plugin.configManager.messages
            val target = args["target"] as? Player

            if (target != null && !player.hasPermission("octane.gmsp.others")) {
                player.sendRichMessage(msgs.prefix + msgs.noPermission)
                return@playerExecutor
            }

            val finalTarget = target ?: player
            finalTarget.gameMode = GameMode.SPECTATOR

            if (target != null) {
                player.sendRichMessage(msgs.prefix + msgs.gmspOther.replace("%player%", finalTarget.name))
                finalTarget.sendRichMessage(msgs.prefix + msgs.gmspTarget.replace("%sender%", player.name))
            } else {
                player.sendRichMessage(msgs.prefix + msgs.gmspSelf)
            }
        }
    }
}
