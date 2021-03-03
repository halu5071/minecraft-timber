package io.moatwel.minecraft.timber.rule

import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

class LeavesMaterialRule : BlockBreakRule {

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
        return targetLeavesList.contains(block.type)
    }
}