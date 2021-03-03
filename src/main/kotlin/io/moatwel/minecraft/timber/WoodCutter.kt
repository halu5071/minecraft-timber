package io.moatwel.minecraft.timber

import io.moatwel.minecraft.timber.rule.PlayerLocationRule
import io.moatwel.minecraft.timber.rule.PlayerRangeRule
import io.moatwel.minecraft.timber.rule.BlockBreakRule
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

class WoodCutter constructor(builder: Builder) {

    private val rules = builder.rules

    fun cut(world: World, player: Player, block: Block) {
        var canAccept = true
        rules.forEach { rule ->
            val result = rule.canAccept(world, player, block)
            canAccept = canAccept && result
        }

        if (canAccept.not()) return

        val mainItem = player.inventory.itemInMainHand
        block.breakNaturally(mainItem)

        for (x in -1..1) {
            for (y in -1..1) {
                for (z in -1..1) {
                    if (x == 0 && y == 0 && z == 0) break

                    val targetBlock = block.getRelative(x, y, z)
                    cut(world, player, targetBlock)
                }
            }
        }
    }

    class Builder {
        val rules = mutableListOf(
            PlayerRangeRule(),
            PlayerLocationRule()
        )

        fun addRule(rule: BlockBreakRule): Builder {
            rules.add(rule)
            return this
        }

        fun build(): WoodCutter {
            return WoodCutter(this)
        }
    }
}