package io.moatwel.minecraft.timber

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class BlockBreakListener(
    plugin: JavaPlugin
) : Listener {

    private val logger = plugin.logger

    companion object {
        @JvmStatic
        fun register(plugin: JavaPlugin) {
            plugin.server.pluginManager.registerEvents(BlockBreakListener(plugin), plugin)
        }
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val mainItem = event.player.inventory.itemInMainHand
        logger.info("onBlockBreak: X: ${event.block.x}, Y: ${event.block.y}, Z: ${event.block.z}, block: ${event.block}, mainItem: $mainItem")
    }
}