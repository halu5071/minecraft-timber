package io.moatwel.minecraft.timber

import io.moatwel.minecraft.timber.rule.BlockBreakRule
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class BlockBreakListener(
    private val woodCutter: WoodCutter,
    private val initialBlockBreakRule: BlockBreakRule
) : Listener {

    companion object {
        @JvmStatic
        fun register(plugin: JavaPlugin,
                     woodCutter: WoodCutter,
                     initialBlockBreakRule: BlockBreakRule) {
            plugin.server.pluginManager.registerEvents(
                BlockBreakListener(woodCutter, initialBlockBreakRule),
                plugin)
        }
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val block = event.block
        val world = block.world
        val player = event.player

        if (initialBlockBreakRule.canAccept(world, player, block).not()) return

        woodCutter.cut(block.world, event.player, block)
    }
}
