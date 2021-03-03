package io.moatwel.minecraft.timber.rule

import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

class WoodLogMaterialRule : BlockBreakRule {

    companion object {
        // TODO: Config 化する
        private val targetWoodList = arrayListOf(
            Material.OAK_LOG,
            Material.DARK_OAK_LOG,
            Material.ACACIA_LOG,
            Material.JUNGLE_LOG,
            Material.SPRUCE_LOG,
            Material.BIRCH_LOG
        )
    }

    override fun canAccept(world: World, player: Player, block: Block): Boolean {
        return targetWoodList.contains(block.type)
    }
}
