package io.moatwel.minecraft.timber.logger

import org.bukkit.plugin.java.JavaPlugin

class TimberLogger {

    companion object {
        private lateinit var plugin: JavaPlugin

        fun plant(plugin: JavaPlugin) {
            this.plugin = plugin
        }

        fun info(message: String) {
            plugin.logger.info(message)
        }
    }
}
