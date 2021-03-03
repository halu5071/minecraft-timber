package io.moatwel.minecraft.timber.rule

import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import kotlin.math.absoluteValue

class PlayerRangeRule(
    private val breakRangeLimit: Int
) : BlockBreakRule {

    override fun canAccept(world: World, player: Player, block: Block): Boolean {
        val playerLocation = player.location
        val blockLocation = block.location

        val isXInRange = (playerLocation.blockX - blockLocation.blockX).absoluteValue <= breakRangeLimit
        val isZInRange = (playerLocation.blockZ - blockLocation.blockZ).absoluteValue <= breakRangeLimit

        return isXInRange && isZInRange
    }
}