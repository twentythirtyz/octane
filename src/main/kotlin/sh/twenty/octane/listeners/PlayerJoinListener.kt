package sh.twenty.octane.listeners

import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import sh.twenty.octane.OctanePlugin

class PlayerJoinListener(private val plugin: OctanePlugin) : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        val msgs = plugin.configManager.messages
        player.sendRichMessage(msgs.joinMessage.replace("%player%", player.name))
        player.health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value ?: 20.0
        player.foodLevel = 20
        player.saturation = 20f
    }
}
