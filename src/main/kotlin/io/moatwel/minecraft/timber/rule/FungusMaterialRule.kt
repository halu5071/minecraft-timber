package io.moatwel.minecraft.timber.rule

import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

class FungusMaterialRule : BlockBreakRule {

    companion object {
        private val targetMaterials = arrayListOf(
            Material.WARPED_STEM,
            Material.CRIMSON_STEM,
            Material.WARPED_WART_BLOCK,
            Material.NETHER_WART_BLOCK,
        )
    }

    override fun canAccept(world: World, player: Player, block: Block): Boolean {
        return targetMaterials.contains(block.type)
    }
}
