package io.moatwel.minecraft.timber.rule

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

class WoodCutRuleTest : ShouldSpec() {

    init {
        context("Multiple rule contains, all rules returns true") {
            val mockRule1 = object : BlockBreakRule {
                override fun canAccept(world: World, player: Player, block: Block) = true
            }
            val mockRule2 = object : BlockBreakRule {
                override fun canAccept(world: World, player: Player, block: Block): Boolean = true
            }

            val group = WoodCutRule.Builder()
                .addRule(mockRule1)
                .addRule(mockRule2)
                .build()

            should("return true if at least one rule can accept") {
                val canAccept = group.canAccept(mockk(), mockk(), mockk())

                canAccept shouldBe true
            }
        }

        context("Multiple rule contains, at least one returns false") {
            val mockRule1 = object : BlockBreakRule {
                override fun canAccept(world: World, player: Player, block: Block) = false
            }
            val mockRule2 = object : BlockBreakRule {
                override fun canAccept(world: World, player: Player, block: Block): Boolean = true
            }

            val group = WoodCutRule.Builder()
                .addRule(mockRule1)
                .addRule(mockRule2)
                .build()

            should("return true if at least one rule can accept") {
                val canAccept = group.canAccept(mockk(), mockk(), mockk())

                canAccept shouldBe false
            }
        }

        context("Multiple rule contains, all rule returns false") {
            val mockRule1 = object : BlockBreakRule {
                override fun canAccept(world: World, player: Player, block: Block) = false
            }
            val mockRule2 = object : BlockBreakRule {
                override fun canAccept(world: World, player: Player, block: Block): Boolean = false
            }

            val group = WoodCutRule.Builder()
                .addRule(mockRule1)
                .addRule(mockRule2)
                .build()

            should("return false") {
                val canAccept = group.canAccept(mockk(), mockk(), mockk())

                canAccept shouldBe false
            }
        }
    }
}