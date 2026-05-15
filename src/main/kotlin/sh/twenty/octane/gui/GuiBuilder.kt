package sh.twenty.octane.gui

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import sh.twenty.octane.config.ItemStateConfig

private val mm = MiniMessage.miniMessage()

class GuiHolder(val handlers: Map<Int, (InventoryClickEvent) -> Unit>) : InventoryHolder {
    private lateinit var inventory: Inventory
    override fun getInventory(): Inventory = inventory
    fun init(inv: Inventory) { inventory = inv }
}

class SlotBuilder {
    var item: ItemStack = ItemStack(Material.AIR)
    private var handler: ((InventoryClickEvent) -> Unit)? = null
    fun onClick(block: (InventoryClickEvent) -> Unit) { handler = block }
    fun handler() = handler
}

class GuiBuilder(private val rows: Int) {
    private val slots = mutableMapOf<Int, SlotBuilder>()

    fun slot(index: Int, block: SlotBuilder.() -> Unit) {
        slots[index] = SlotBuilder().apply(block)
    }

    fun open(player: Player, title: String) {
        val handlers = slots.mapNotNull { (i, b) -> b.handler()?.let { i to it } }.toMap()
        val holder = GuiHolder(handlers)
        val inv = Bukkit.createInventory(holder, rows * 9, mm.deserialize(title))
        holder.init(inv)
        slots.forEach { (i, b) -> inv.setItem(i, b.item) }
        player.openInventory(inv)
    }
}

fun Player.openGui(title: String, rows: Int, block: GuiBuilder.() -> Unit) =
    GuiBuilder(rows).apply(block).open(this, title)

fun ItemStateConfig.toItem(): ItemStack {
    val mat = Material.matchMaterial(material) ?: Material.BEDROCK
    val displayName = mm.deserialize(name)
    val itemLore = lore.map { mm.deserialize(it) }
    return ItemStack(mat).apply {
        itemMeta = itemMeta?.apply {
            displayName(displayName)
            lore(itemLore)
        }
    }
}
