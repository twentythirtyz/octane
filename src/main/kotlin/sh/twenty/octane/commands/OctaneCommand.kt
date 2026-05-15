package sh.twenty.octane.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.literalArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import sh.twenty.octane.OctanePlugin

class OctaneCommand(private val plugin: OctanePlugin) {
    fun register() = commandAPICommand("octane") {
        withPermission("octane.admin")
        literalArgument("reload")
        playerExecutor { player, _ ->
            plugin.configManager.reload()
            val msgs = plugin.configManager.messages
            player.sendRichMessage(msgs.prefix + msgs.configReloaded)
        }
    }
}
