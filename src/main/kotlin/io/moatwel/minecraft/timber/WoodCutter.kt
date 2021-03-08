package io.moatwel.minecraft.timber

import io.moatwel.minecraft.timber.rule.WoodCutRule
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player

class WoodCutter(private val woodCutRule: WoodCutRule) {

    fun cut(world: World, player: Player, block: Block) {
        val canAccept = woodCutRule.canAccept(world, player, block)
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
}
