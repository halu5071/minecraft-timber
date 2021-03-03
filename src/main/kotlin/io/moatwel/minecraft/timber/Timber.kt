package io.moatwel.minecraft.timber

import io.moatwel.minecraft.timber.rule.BlockBreakRuleGroup
import io.moatwel.minecraft.timber.rule.LeavesMaterialRule
import io.moatwel.minecraft.timber.rule.PlayerItemRule
import io.moatwel.minecraft.timber.rule.WoodCutRule
import io.moatwel.minecraft.timber.rule.WoodLogMaterialRule
import org.bukkit.plugin.java.JavaPlugin

class Timber : JavaPlugin() {

    private val woodCutRule = WoodCutRule.Builder()
        .addRule(PlayerItemRule())
        .addRule(BlockBreakRuleGroup.Builder()
            .addRule(WoodLogMaterialRule())
            .addRule(LeavesMaterialRule())
            .build())
        .build()
    private val woodCutter = WoodCutter(woodCutRule)

    override fun onEnable() {
        super.onEnable()

        BlockBreakListener.register(this, woodCutter)
    }
}