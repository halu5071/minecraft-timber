package io.moatwel.minecraft.timber.config

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.java.JavaPlugin


private const val KEY_SHOULD_BREAK_LEAVES = "should_break_leaves"
private const val KEY_IS_ENABLE_PLUGIN = "is_enable_plugin"
private const val TRUE = 0
private const val FALSE = 1

fun Player.enableLeavesBreak(plugin: JavaPlugin) {
    val key = NamespacedKey(plugin, KEY_SHOULD_BREAK_LEAVES)
    persistentDataContainer.set(
        key,
        PersistentDataType.INTEGER,
        TRUE)
}

fun Player.disableLeavesBreak(plugin: JavaPlugin) {
    val key = NamespacedKey(plugin, KEY_SHOULD_BREAK_LEAVES)
    persistentDataContainer.set(
        key,
        PersistentDataType.INTEGER,
        FALSE)
}

fun Player.shouldBreakLeaves(plugin: JavaPlugin): Boolean {
    val key = NamespacedKey(plugin, KEY_SHOULD_BREAK_LEAVES)
    val storedValue = persistentDataContainer.getOrDefault(
        key,
        PersistentDataType.INTEGER,
        TRUE)
    return storedValue == TRUE
}

// ===============================================

fun Player.enablePlugin(plugin: JavaPlugin) {
    val key = NamespacedKey(plugin, KEY_IS_ENABLE_PLUGIN)
    persistentDataContainer.set(
        key,
        PersistentDataType.INTEGER,
        TRUE)
}

fun Player.disablePlugin(plugin: JavaPlugin) {
    val key = NamespacedKey(plugin, KEY_IS_ENABLE_PLUGIN)
    persistentDataContainer.set(
        key,
        PersistentDataType.INTEGER,
        FALSE)
}

fun Player.isEnabledPlugin(plugin: JavaPlugin): Boolean {
    val key = NamespacedKey(plugin, KEY_IS_ENABLE_PLUGIN)
    val storedValue = persistentDataContainer.getOrDefault(
        key,
        PersistentDataType.INTEGER,
        TRUE)
    return storedValue == TRUE
}
