package sh.twenty.octane.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import sh.twenty.octane.OctanePlugin

class HealCommand(private val plugin: OctanePlugin) {
    fun register() = commandAPICommand("heal") {
        withPermission("octane.heal")
        playerArgument("target", optional = true)
        playerExecutor { player, args ->
            val msgs = plugin.configManager.messages
            val target = args["target"] as? Player

            if (target != null && !player.hasPermission("octane.heal.others")) {
                player.sendRichMessage(msgs.prefix + msgs.noPermission)
                return@playerExecutor
            }

            val finalTarget = target ?: player
            finalTarget.health = finalTarget.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value ?: 20.0

            if (target != null) {
                player.sendRichMessage(msgs.prefix + msgs.healOther.replace("%player%", finalTarget.name))
                finalTarget.sendRichMessage(msgs.prefix + msgs.healTarget.replace("%sender%", player.name))
            } else {
                player.sendRichMessage(msgs.prefix + msgs.healSelf)
            }
        }
    }
}
