package io.moatwel.minecraft.timber.rule

import io.moatwel.minecraft.timber.config.ServerConfig
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class WoodCutRule internal constructor(builder: Builder) : BlockBreakRule {

    private val rules = builder.rules

    companion object {
        fun create(plugin: JavaPlugin): WoodCutRule {
            val serverConfig = ServerConfig(plugin)

            val woodCutRuleBuilder = Builder()
            woodCutRuleBuilder
                .addRule(PlayerItemRule())

            val materialRuleGroup = BlockBreakRuleGroup.Builder()

            materialRuleGroup
                .addRule(WoodLogMaterialRule())
                .addRule(LeavesMaterialRule(plugin))
                .addRule(FungusMaterialRule())

            val breakRangeLimit = serverConfig.getXZBreakRangeLimit()
            woodCutRuleBuilder
                .addRule(PlayerRangeRule(breakRangeLimit))

            woodCutRuleBuilder.addRule(materialRuleGroup.build())

            return woodCutRuleBuilder.build()
        }
    }

    override fun canAccept(world: World, player: Player, block: Block): Boolean {
        var canAccept = true
        rules.forEach { rule ->
            val result = rule.canAccept(world, player, block)
            canAccept = canAccept && result
        }
        return canAccept
    }

    class Builder {
        internal val rules: MutableList<BlockBreakRule> = mutableListOf(
            PlayerLocationRule()
        )

        fun addRule(rule: BlockBreakRule): Builder {
            rules.add(rule)
            return this
        }

        fun build(): WoodCutRule {
            return WoodCutRule(this)
        }
    }
}