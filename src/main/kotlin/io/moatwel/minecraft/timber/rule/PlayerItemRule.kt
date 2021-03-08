package io.moatwel.minecraft.timber.rule

import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

class PlayerItemRule : BlockBreakRule {

    companion object {
        private val allowedItems = arrayListOf(
            Material.WOODEN_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.GOLDEN_AXE,
            Material.DIAMOND_AXE,
        )
    }

    override fun canAccept(world: World, player: Player, block: Block): Boolean {
        return allowedItems.contains(player.inventory.itemInMainHand.type)
    }
}
