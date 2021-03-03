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
        val block = event.block

        woodCutter.cut(block.world, event.player, block)
    }
}