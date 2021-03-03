package io.moatwel.minecraft.timber.rule

import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import kotlin.math.absoluteValue

class PlayerRangeRule : BlockBreakRule {

    companion object {
        private const val RANGE_LIMIT = 10
    }

    override fun canAccept(world: World, player: Player, block: Block): Boolean {
        val playerLocation = player.location
        val blockLocation = block.location

        val isXInRange = (playerLocation.blockX - blockLocation.blockX).absoluteValue <= RANGE_LIMIT
        val isYInRange = (playerLocation.blockY - blockLocation.blockY).absoluteValue <= RANGE_LIMIT
        val isZInRange = (playerLocation.blockZ - blockLocation.blockZ).absoluteValue <= RANGE_LIMIT

        return isXInRange && isYInRange && isZInRange
    }
}