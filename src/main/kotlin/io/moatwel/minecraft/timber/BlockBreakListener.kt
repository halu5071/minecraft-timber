package io.moatwel.minecraft.timber

import io.moatwel.minecraft.timber.rule.WoodLogMaterialRule
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class BlockBreakListener(
    private val woodCutter: WoodCutter
) : Listener {

    private val woodMaterialRule = WoodLogMaterialRule()

    companion object {
        @JvmStatic
        fun register(plugin: JavaPlugin, woodCutter: WoodCutter) {
            plugin.server.pluginManager.registerEvents(
                BlockBreakListener(woodCutter),
                plugin)
        }
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val block = event.block
        val world = block.world
        val player = event.player

        if (woodMaterialRule.canAccept(world, player, block).not()) return

        woodCutter.cut(block.world, event.player, block)
    }
}