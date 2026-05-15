package sh.twenty.octane.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.entity.Player
import sh.twenty.octane.OctanePlugin

class FeedCommand(private val plugin: OctanePlugin) {
    fun register() = commandAPICommand("feed") {
        withPermission("octane.feed")
        playerArgument("target", optional = true)
        playerExecutor { player, args ->
            val msgs = plugin.configManager.messages
            val target = args["target"] as? Player

            if (target != null && !player.hasPermission("octane.feed.others")) {
                player.sendRichMessage(msgs.prefix + msgs.noPermission)
                return@playerExecutor
            }

            val finalTarget = target ?: player
            finalTarget.foodLevel = 20
            finalTarget.saturation = 20f

            if (target != null) {
                player.sendRichMessage(msgs.prefix + msgs.feedOther.replace("%player%", finalTarget.name))
                finalTarget.sendRichMessage(msgs.prefix + msgs.feedTarget.replace("%sender%", player.name))
            } else {
                player.sendRichMessage(msgs.prefix + msgs.feedSelf)
            }
        }
    }
}
