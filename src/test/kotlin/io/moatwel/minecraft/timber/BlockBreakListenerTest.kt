package io.moatwel.minecraft.timber

import io.kotest.core.spec.style.ShouldSpec
import io.moatwel.minecraft.timber.rule.BlockBreakRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakListenerTest : ShouldSpec() {

    private val mockWorld = mockk<World>()
    private val mockPlayer = mockk<Player>()
    private val mockBlock = mockk<Block>()

    private val event = mockk<BlockBreakEvent>().apply {
        every { block }.returns(mockBlock)
        every { mockBlock.world }.returns(mockWorld)
        every { player }.returns(mockPlayer)
    }

    init {
        context("When initialBreakRule can be acceptable") {
            val cutter = mockk<WoodCutter>().apply {
                every { cut(mockWorld, mockPlayer, mockBlock) }.returns(Unit)
            }
            val initialBreakRule = mockk<BlockBreakRule>().apply {
                every { canAccept(mockWorld, mockPlayer, mockBlock) }.returns(true)
            }
            val listener = BlockBreakListener(cutter, initialBreakRule)

            should("be called WoodCutter.cut()") {
                listener.onBlockBreak(event)

                verify(exactly = 1) {
                    cutter.cut(mockWorld, mockPlayer, mockBlock)
                }
            }
        }

        context("When initialBreakRule can not be acceptable") {
            val cutter = mockk<WoodCutter>().apply {
                every { cut(mockWorld, mockPlayer, mockBlock) }.returns(Unit)
            }
            val initialBreakRule = mockk<BlockBreakRule>().apply {
                every { canAccept(mockWorld, mockPlayer, mockBlock) }.returns(false)
            }
            val listener = BlockBreakListener(cutter, initialBreakRule)

            should("not be called WoodCutter.cut()") {
                listener.onBlockBreak(event)

                verify(exactly = 0) {
                    cutter.cut(mockWorld, mockPlayer, mockBlock)
                }
            }
        }
    }
}