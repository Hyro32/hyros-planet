package one.hyro.listeners

import io.papermc.paper.chat.ChatRenderer
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import one.hyro.HyroCore
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object AsyncChatListener: Listener, ChatRenderer {
    private val configuration: FileConfiguration? = HyroCore.instance?.config
    private val isPerWorldChat: Boolean = configuration?.getBoolean("chat.per-world-chat") ?: false

    @EventHandler
    fun onAsyncChat(event: AsyncChatEvent) {
        event.renderer(this::render)
        val player: Player = event.player
        val message: Component = event.message()

        if (isPerWorldChat) {
            val render: Component = player.displayName().color(NamedTextColor.DARK_GRAY)
                .append(Component.text(": ", NamedTextColor.DARK_GRAY))
                .append(message.color(NamedTextColor.GRAY))

            event.isCancelled = true
            event.viewers().forEach { viewer -> viewer.sendMessage(render) }
        }
    }

    override fun render(player: Player, displayName: Component, message: Component, audience: Audience): Component {
        return displayName.color(NamedTextColor.DARK_GRAY)
            .append(Component.text(": ", NamedTextColor.DARK_GRAY))
            .append(message.color(NamedTextColor.GRAY))
    }
}