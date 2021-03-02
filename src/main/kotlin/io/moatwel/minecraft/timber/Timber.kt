package io.moatwel.minecraft.timber

import org.bukkit.plugin.java.JavaPlugin

class Timber : JavaPlugin() {

    override fun onEnable() {
        super.onEnable()
        logger.info("onEnable")

        BlockBreakListener.register(this)
    }

    override fun onDisable() {
        super.onDisable()
        logger.info("onDisable")
    }
}