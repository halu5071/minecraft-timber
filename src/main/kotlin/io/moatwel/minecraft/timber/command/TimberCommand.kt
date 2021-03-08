package io.moatwel.minecraft.timber.command

import io.moatwel.minecraft.timber.config.disableLeavesBreak
import io.moatwel.minecraft.timber.config.disablePlugin
import io.moatwel.minecraft.timber.config.enableLeavesBreak
import io.moatwel.minecraft.timber.config.enablePlugin
import io.moatwel.minecraft.timber.config.shouldBreakLeaves
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class TimberCommand(
    private val plugin: JavaPlugin
) : CommandExecutor {

    companion object {
        private const val COMMAND = "timber"

        private const val TARGET_LEAVE = "leave"
        private const val ON = "on"
        private const val OFF = "off"
        private const val STATUS = "status"

        fun register(plugin: JavaPlugin) {
            plugin.getCommand(COMMAND)?.setExecutor(TimberCommand(plugin))
        }
    }

    override fun onCommand(sender: CommandSender,
                           command: Command,
                           label: String,
                           args: Array<out String>): Boolean {

        when (args.getOrNull(0)) {
            TARGET_LEAVE -> handleLeaves(sender, args.getOrNull(1))
            ON,
            OFF -> handlePlugin(sender, args.getOrNull(0))
            else -> help(sender)
        }

        return true
    }

    private fun handlePlugin(sender: CommandSender, arg1: String?) {
        if ((sender is Player).not()) {
            sender.sendMessage("You do not have permission.")
            return
        }

        sender as Player

        when (arg1) {
            ON -> {
                sender.enablePlugin(plugin)
                sender.sendMessages("[Timber] Plugin: ON")
            }
            OFF -> {
                sender.disablePlugin(plugin)
                sender.sendMessages("[Timber] Plugin: OFF")
            }
            else -> help(sender)
        }
    }

    private fun handleLeaves(sender: CommandSender, arg1: String?) {
        if ((sender is Player).not()) {
            sender.sendMessage("You do not have permission.")
            return
        }

        sender as Player

        when (arg1) {
            ON -> {
                sender.enableLeavesBreak(plugin)
                sender.sendMessages("[Timber] Leaves Breaking: ON")
            }
            OFF -> {
                sender.disableLeavesBreak(plugin)
                sender.sendMessages("[Timber] Leaves Breaking: OFF")
            }
            STATUS -> {
                val status = sender.shouldBreakLeaves(plugin)
                sender.sendMessages("[Timber] Leaves Breaking: ${if (status) "ON" else "OFF"}")
            }
            else -> help(sender)
        }
    }

    private fun help(sender: CommandSender) {
        sender.sendMessage(
            "[Timber] No Help 8-((((")
    }
}
