package sh.twenty.octane.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.GameMode
import org.bukkit.entity.Player
import sh.twenty.octane.OctanePlugin

class GmsCommand(private val plugin: OctanePlugin) {
    fun register() = commandAPICommand("gms") {
        withPermission("octane.gms")
        playerArgument("target", optional = true)
        playerExecutor { player, args ->
            val msgs = plugin.configManager.messages
            val target = args["target"] as? Player

            if (target != null && !player.hasPermission("octane.gms.others")) {
                player.sendRichMessage(msgs.prefix + msgs.noPermission)
                return@playerExecutor
            }

            val finalTarget = target ?: player
            finalTarget.gameMode = GameMode.SURVIVAL

            if (target != null) {
                player.sendRichMessage(msgs.prefix + msgs.gmsOther.replace("%player%", finalTarget.name))
                finalTarget.sendRichMessage(msgs.prefix + msgs.gmsTarget.replace("%sender%", player.name))
            } else {
                player.sendRichMessage(msgs.prefix + msgs.gmsSelf)
            }
        }
    }
}
