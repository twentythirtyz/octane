package sh.twenty.octane.gui

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class GuiListener : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val holder = event.view.topInventory.holder as? GuiHolder ?: return
        event.isCancelled = true
        holder.handlers[event.rawSlot]?.invoke(event)
    }
}
