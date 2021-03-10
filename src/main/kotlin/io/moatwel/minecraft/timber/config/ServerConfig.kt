package io.moatwel.minecraft.timber.config

import org.bukkit.plugin.java.JavaPlugin

class ServerConfig(
    private val plugin: JavaPlugin
) {

    companion object {
        private const val X_Z_BREAK_RANGE_LIMIT = "x_z_break_range_limit"

        private const val DEFAULT_RANGE_LIMIT = 5
    }

    fun getXZBreakRangeLimit(): Int {
        val rangeLimit = plugin.config.getInt(X_Z_BREAK_RANGE_LIMIT)
        return if (rangeLimit < DEFAULT_RANGE_LIMIT) DEFAULT_RANGE_LIMIT else rangeLimit
    }
}
