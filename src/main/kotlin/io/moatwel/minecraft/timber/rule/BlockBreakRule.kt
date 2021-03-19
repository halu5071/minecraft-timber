package io.moatwel.minecraft.timber.rule

import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

interface BlockBreakRule {

    fun canAccept(world: World, player: Player, block: Block): Boolean
}
