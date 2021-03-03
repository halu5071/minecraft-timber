package io.moatwel.minecraft.timber.rule

import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

class PlayerLocationRule : BlockBreakRule {

    override fun canAccept(world: World, player: Player, block: Block): Boolean {
        val playerLocation = player.location

        return block.location.blockY >= playerLocation.blockY
    }
}
