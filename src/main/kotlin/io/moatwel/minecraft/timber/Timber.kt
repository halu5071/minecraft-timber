package io.moatwel.minecraft.timber

import io.moatwel.minecraft.timber.config.ServerConfig
import io.moatwel.minecraft.timber.logger.TimberLogger
import io.moatwel.minecraft.timber.rule.BlockBreakRuleGroup
import io.moatwel.minecraft.timber.rule.FungusMaterialRule
import io.moatwel.minecraft.timber.rule.LeavesMaterialRule
import io.moatwel.minecraft.timber.rule.PlayerItemRule
import io.moatwel.minecraft.timber.rule.PlayerRangeRule
import io.moatwel.minecraft.timber.rule.WoodCutRule
import io.moatwel.minecraft.timber.rule.WoodLogMaterialRule
import org.bukkit.plugin.java.JavaPlugin

class Timber : JavaPlugin() {

    override fun onEnable() {
        super.onEnable()

        TimberLogger.plant(this)

        saveDefaultConfig()

        val woodCutRule = createWoodCutRule()

        BlockBreakListener.register(this, WoodCutter(woodCutRule))
    }

    private fun createWoodCutRule(): WoodCutRule {
        val serverConfig = ServerConfig(this)

        val woodCutRuleBuilder = WoodCutRule.Builder()
        woodCutRuleBuilder
            .addRule(PlayerItemRule())

        val materialRuleGroup = BlockBreakRuleGroup.Builder()

        if (serverConfig.shouldBreakLeaves()) {
            materialRuleGroup
                .addRule(WoodLogMaterialRule())
                .addRule(LeavesMaterialRule())
                .addRule(FungusMaterialRule())
        } else {
            // Do not add LeavesMaterialRule()
            materialRuleGroup
                .addRule(WoodLogMaterialRule())
                .addRule(FungusMaterialRule())
        }

        val breakRangeLimit = serverConfig.getXZBreakRangeLimit()
        woodCutRuleBuilder
            .addRule(PlayerRangeRule(breakRangeLimit))

        woodCutRuleBuilder.addRule(materialRuleGroup.build())

        return woodCutRuleBuilder.build()
    }
}