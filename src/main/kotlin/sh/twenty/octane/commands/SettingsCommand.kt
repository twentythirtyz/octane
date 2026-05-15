package sh.twenty.octane.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.entity.Player
import sh.twenty.octane.OctanePlugin
import sh.twenty.octane.gui.openGui
import sh.twenty.octane.gui.toItem

class SettingsCommand(private val plugin: OctanePlugin) {
    fun register() = commandAPICommand("settings") {
        withPermission("octane.admin")
        playerExecutor { player, _ -> openSettingsGui(player) }
    }

    fun openSettingsGui(player: Player) {
        val menu = plugin.configManager.config.menus.settings
        val world = player.world

        player.openGui(menu.title, menu.size / 9) {
            with(menu.items) {
                slot(pvp.slot) {
                    item = pvp.state(world.pvp).toItem()
                    onClick {
                        world.pvp = !world.pvp
                        openSettingsGui(player)
                    }
                }
                slot(time.slot) {
                    item = time.state(world.time < 13000).toItem()
                    onClick {
                        world.time = if (world.time < 13000) 13000L else 1000L
                        openSettingsGui(player)
                    }
                }
                slot(weather.slot) {
                    item = weather.state(world.hasStorm()).toItem()
                    onClick {
                        val newState = !world.hasStorm()
                        world.setStorm(newState)
                        world.setThundering(newState)
                        openSettingsGui(player)
                    }
                }
            }
        }
    }
}
