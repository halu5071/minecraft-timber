package io.moatwel.minecraft.timber

import io.moatwel.minecraft.timber.rule.PlayerItemRule
import io.moatwel.minecraft.timber.rule.WoodMaterialRule
import org.bukkit.plugin.java.JavaPlugin

class Timber : JavaPlugin() {

    private val woodCutter = WoodCutter.Builder()
        .addRule(PlayerItemRule())
        .addRule(WoodMaterialRule())
        .build()

    override fun onEnable() {
        super.onEnable()
        logger.info("onEnable")

        BlockBreakListener.register(this, woodCutter)
    }

    override fun onDisable() {
        super.onDisable()
        logger.info("onDisable")
    }
}