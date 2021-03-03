package io.moatwel.minecraft.timber.rule

import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

class BlockBreakRuleGroup(builder: Builder) : BlockBreakRule {

    private val rules = builder.rules

    override fun canAccept(world: World, player: Player, block: Block): Boolean {
        var canAccept = false
        rules.forEach { rule ->
            val result = rule.canAccept(world, player, block)
            canAccept = canAccept || result
        }
        return canAccept
    }

    class Builder {
        internal val rules = mutableListOf<BlockBreakRule>()

        fun addRule(rule: BlockBreakRule): Builder {
            rules.add(rule)
            return this
        }

        fun build(): BlockBreakRuleGroup {
            return BlockBreakRuleGroup(this)
        }
    }
}
