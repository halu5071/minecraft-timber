package io.moatwel.minecraft.timber.rule

import io.moatwel.minecraft.timber.config.shouldBreakLeaves
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class LeavesMaterialRule(
    private val plugin: JavaPlugin
) : BlockBreakRule {

    companion object {
        private val targetLeavesList = arrayListOf(
            Material.ACACIA_LEAVES,
            Material.BIRCH_LEAVES,
            Material.DARK_OAK_LEAVES,
            Material.JUNGLE_LEAVES,
            Material.OAK_LEAVES,
            Material.SPRUCE_LEAVES
        )
    }

    override fun canAccept(world: World, player: Player, block: Block): Boolean {
        if (player.shouldBreakLeaves(plugin).not()) return false

        return targetLeavesList.contains(block.type)
    }
}
