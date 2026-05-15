package sh.twenty.octane.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.entity.Player
import sh.twenty.octane.OctanePlugin

class FlyCommand(private val plugin: OctanePlugin) {
    fun register() = commandAPICommand("fly") {
        withPermission("octane.fly")
        playerArgument("target", optional = true)
        playerExecutor { player, args ->
            val msgs = plugin.configManager.messages
            val target = args["target"] as? Player

            if (target != null && !player.hasPermission("octane.fly.others")) {
                player.sendRichMessage(msgs.prefix + msgs.noPermission)
                return@playerExecutor
            }

            val finalTarget = target ?: player
            val enabled = !finalTarget.allowFlight
            finalTarget.allowFlight = enabled
            if (!enabled) finalTarget.isFlying = false

            if (target != null) {
                val key = if (enabled) msgs.flyEnabledOther else msgs.flyDisabledOther
                player.sendRichMessage(msgs.prefix + key.replace("%player%", finalTarget.name))
                val targetKey = if (enabled) msgs.flyEnabledTarget else msgs.flyDisabledTarget
                finalTarget.sendRichMessage(msgs.prefix + targetKey.replace("%sender%", player.name))
            } else {
                player.sendRichMessage(msgs.prefix + if (enabled) msgs.flyEnabled else msgs.flyDisabled)
            }
        }
    }
}
