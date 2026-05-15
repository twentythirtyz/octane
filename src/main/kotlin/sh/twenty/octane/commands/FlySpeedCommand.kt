package sh.twenty.octane.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.entity.Player
import sh.twenty.octane.OctanePlugin

class FlySpeedCommand(private val plugin: OctanePlugin) {
    fun register() = commandAPICommand("flyspeed") {
        withPermission("octane.flyspeed")
        integerArgument("speed", 1, 10)
        playerArgument("target", optional = true)
        playerExecutor { player, args ->
            val msgs = plugin.configManager.messages
            val speed = args["speed"] as Int
            val target = args["target"] as? Player

            if (target != null && !player.hasPermission("octane.flyspeed.others")) {
                player.sendRichMessage(msgs.prefix + msgs.noPermission)
                return@playerExecutor
            }

            val finalTarget = target ?: player
            finalTarget.flySpeed = speed / 10f

            if (target != null) {
                player.sendRichMessage(msgs.prefix + msgs.flyspeedOther
                    .replace("%player%", finalTarget.name).replace("%speed%", speed.toString()))
                finalTarget.sendRichMessage(msgs.prefix + msgs.flyspeedTarget
                    .replace("%sender%", player.name).replace("%speed%", speed.toString()))
            } else {
                player.sendRichMessage(msgs.prefix + msgs.flyspeedSelf.replace("%speed%", speed.toString()))
            }
        }
    }
}
