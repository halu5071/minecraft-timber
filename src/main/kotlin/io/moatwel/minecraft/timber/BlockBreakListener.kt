package io.moatwel.minecraft.timber

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class BlockBreakListener(
    plugin: JavaPlugin,
    private val woodCutter: WoodCutter
) : Listener {

    private val logger = plugin.logger

    companion object {
        @JvmStatic
        fun register(plugin: JavaPlugin, woodCutter: WoodCutter) {
            plugin.server.pluginManager.registerEvents(
                BlockBreakListener(plugin, woodCutter),
                plugin)
        }
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val mainItem = event.player.inventory.itemInMainHand
        val block = event.block
        logger.info("onBlockBreak: X: ${event.block.x}, Y: ${event.block.y}, Z: ${event.block.z}, block: ${event.block}")
        logger.info("onBlockBreak: block: $block, data: ${block.blockData}")
        logger.info("onBlockBreak: item: $mainItem, type: ${mainItem.type}")

        woodCutter.cut(block.world, event.player, block)
    }
}