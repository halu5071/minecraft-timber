package io.moatwel.minecraft.timber

import io.kotest.core.spec.style.ShouldSpec
import io.moatwel.minecraft.timber.rule.WoodCutRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

class WoodCutterTest : ShouldSpec() {

    init {
        context("WoodCutRule will not accept world/player/block") {
            val mockWorld = mockk<World>()
            val mockPlayer = mockk<Player>()
            val mockBlock = mockk<Block>()
            val woodCutRule = mockk<WoodCutRule>().apply {
                every { canAccept(mockWorld, mockPlayer, mockBlock) }.returns(false)
            }
            val cutter = WoodCutter(woodCutRule)
            should("not be called Block.breakNaturally()") {
                cutter.cut(mockWorld, mockPlayer, mockBlock)

                verify(exactly = 0) {
                    mockBlock.breakNaturally(any())
                }
            }
        }
    }
}
