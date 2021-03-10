package io.moatwel.minecraft.timber

import io.moatwel.minecraft.timber.command.TimberCommand
import io.moatwel.minecraft.timber.logger.TimberLogger
import io.moatwel.minecraft.timber.rule.InitialBlockBreakRule
import io.moatwel.minecraft.timber.rule.WoodCutRule
import org.bukkit.plugin.java.JavaPlugin

class Timber : JavaPlugin() {

    override fun onEnable() {
        super.onEnable()

        TimberLogger.plant(this)

        saveDefaultConfig()

        val woodCutRule = WoodCutRule.create(this)
        val initialRule = InitialBlockBreakRule(this)

        BlockBreakListener.register(this, WoodCutter(woodCutRule), initialRule)

        TimberCommand.register(this)
    }
}
