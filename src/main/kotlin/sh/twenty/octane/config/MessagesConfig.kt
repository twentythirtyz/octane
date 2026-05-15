package sh.twenty.octane.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessagesConfig(
    val prefix: String = "<dark_gray>[<aqua>Octane<dark_gray>] <reset>",

    @SerialName("no-permission")
    val noPermission: String = "<red>You do not have permission!",
    @SerialName("player-not-found")
    val playerNotFound: String = "<red>Could not find player: <white>%player%",

    @SerialName("heal-self")   val healSelf: String   = "<gray>You have been fully healed.",
    @SerialName("heal-other")  val healOther: String  = "<gray>Healed <white>%player%<gray>.",
    @SerialName("heal-target") val healTarget: String = "<gray>You were healed by <white>%sender%<gray>.",

    @SerialName("feed-self")   val feedSelf: String   = "<gray>Your appetite has been sated.",
    @SerialName("feed-other")  val feedOther: String  = "<gray>You have sated <white>%player%'s<gray> appetite.",
    @SerialName("feed-target") val feedTarget: String = "<gray>Your appetite was sated by <white>%sender%<gray>.",

    @SerialName("gmc-self")    val gmcSelf: String    = "<gray>Your gamemode was set to <white>Creative<gray>.",
    @SerialName("gmc-other")   val gmcOther: String   = "<gray><white>%player%<gray>'s gamemode was set to <white>Creative<gray>.",
    @SerialName("gmc-target")  val gmcTarget: String  = "<gray>Your gamemode was set to <white>Creative<gray> by <white>%sender%<gray>.",

    @SerialName("gms-self")    val gmsSelf: String    = "<gray>Your gamemode was set to <white>Survival<gray>.",
    @SerialName("gms-other")   val gmsOther: String   = "<gray><white>%player%<gray>'s gamemode was set to <white>Survival<gray>.",
    @SerialName("gms-target")  val gmsTarget: String  = "<gray>Your gamemode was set to <white>Survival<gray> by <white>%sender%<gray>.",

    @SerialName("gmsp-self")   val gmspSelf: String   = "<gray>Your gamemode was set to <white>Spectator<gray>.",
    @SerialName("gmsp-other")  val gmspOther: String  = "<gray><white>%player%<gray>'s gamemode was set to <white>Spectator<gray>.",
    @SerialName("gmsp-target") val gmspTarget: String = "<gray>Your gamemode was set to <white>Spectator<gray> by <white>%sender%<gray>.",

    @SerialName("join-message")
    val joinMessage: String = "<white>Coming soon!",
    @SerialName("config-reloaded")
    val configReloaded: String = "<gray>Configuration reloaded.",
    @SerialName("octane-usage")
    val octaneUsage: String = "<gray>Usage: <white>/octane reload",
)
