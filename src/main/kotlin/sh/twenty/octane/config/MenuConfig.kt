package sh.twenty.octane.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OctaneConfig(
    val menus: MenusConfig = MenusConfig()
)

@Serializable
data class MenusConfig(
    val settings: SettingsMenuConfig = SettingsMenuConfig()
)

@Serializable
data class SettingsMenuConfig(
    val title: String = "<dark_gray>Server Settings",
    val size: Int = 27,
    val items: SettingsItemsConfig = SettingsItemsConfig()
)

@Serializable
data class SettingsItemsConfig(
    val pvp: ToggleItemConfig = ToggleItemConfig(
        slot = 11,
        trueState  = ItemStateConfig("LIME_WOOL", "<yellow><bold>PvP Toggle",
            listOf("<gray>Current: <green>Enabled", "", "<yellow>Click to disable!")),
        falseState = ItemStateConfig("RED_WOOL",  "<yellow><bold>PvP Toggle",
            listOf("<gray>Current: <red>Disabled", "", "<yellow>Click to enable!"))
    ),
    val time: ToggleItemConfig = ToggleItemConfig(
        slot = 13,
        trueState  = ItemStateConfig("SUNFLOWER", "<aqua><bold>Time Toggle",
            listOf("<gray>Current: <yellow>Day", "", "<yellow>Click to set Night!")),
        falseState = ItemStateConfig("CLOCK",     "<aqua><bold>Time Toggle",
            listOf("<gray>Current: <blue>Night", "", "<yellow>Click to set Day!"))
    ),
    val weather: ToggleItemConfig = ToggleItemConfig(
        slot = 15,
        trueState  = ItemStateConfig("WATER_BUCKET",  "<light_purple><bold>Weather Toggle",
            listOf("<gray>Current: <blue>Raining", "", "<yellow>Click to clear weather!")),
        falseState = ItemStateConfig("LAVA_BUCKET", "<light_purple><bold>Weather Toggle",
            listOf("<gray>Current: <gold>Clear", "", "<yellow>Click to make it rain!"))
    )
)

@Serializable
data class ToggleItemConfig(
    val slot: Int,
    @SerialName("true-state")  val trueState: ItemStateConfig,
    @SerialName("false-state") val falseState: ItemStateConfig,
) {
    fun state(active: Boolean) = if (active) trueState else falseState
}

@Serializable
data class ItemStateConfig(
    val material: String,
    val name: String,
    val lore: List<String> = emptyList(),
)
