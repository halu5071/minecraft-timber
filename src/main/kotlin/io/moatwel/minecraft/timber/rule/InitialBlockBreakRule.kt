package io.moatwel.minecraft.timber.rule

import io.moatwel.minecraft.timber.config.isEnabledPlugin
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class InitialBlockBreakRule(
    private val plugin: JavaPlugin
) : BlockBreakRule {

    companion object {
        private val targetBlockList = arrayListOf(
            Material.OAK_LOG,
            Material.DARK_OAK_LOG,
            Material.ACACIA_LOG,
            Material.JUNGLE_LOG,
            Material.SPRUCE_LOG,
            Material.BIRCH_LOG,

            Material.WARPED_STEM,
            Material.CRIMSON_STEM,
        )
    }

    override fun canAccept(world: World, player: Player, block: Block): Boolean {
        val isTargetBlock = targetBlockList.contains(block.type)
        val isEnabledPlugin = player.isEnabledPlugin(plugin)

        return isTargetBlock && isEnabledPlugin
    }
}