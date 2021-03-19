package io.moatwel.minecraft.timber.command

import io.kotest.core.spec.style.ShouldSpec
import io.moatwel.minecraft.timber.config.disableLeavesBreak
import io.moatwel.minecraft.timber.config.disablePlugin
import io.moatwel.minecraft.timber.config.enableLeavesBreak
import io.moatwel.minecraft.timber.config.enablePlugin
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.bukkit.command.Command
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class TimberCommandTest : ShouldSpec() {

    private val mockPlugin = mockk<JavaPlugin>()
    private val mockSender = mockk<Player>()
    private val mockCommand = mockk<Command>()

    init {
        fun readyExtensions() {
            mockkStatic("io.moatwel.minecraft.timber.config.PlayerConfigKt")
            every { mockSender.enablePlugin(mockPlugin) }.returns(Unit)
            every { mockSender.disablePlugin(mockPlugin) }.returns(Unit)
            every { mockSender.enableLeavesBreak(mockPlugin) }.returns(Unit)
            every { mockSender.disableLeavesBreak(mockPlugin) }.returns(Unit)

            mockkStatic("io.moatwel.minecraft.timber.command.CommandSenderKt")
            every { mockSender.sendMessages(any()) }.returns(Unit)
        }

        val command = TimberCommand(mockPlugin)

        context("Plugin setting") {
            context("User enable plugin") {
                readyExtensions()
                val args = arrayOf("on")

                should("be called Player.enablePlugin()") {
                    command.onCommand(mockSender, mockCommand, "", args)

                    verify(exactly = 1) {
                        mockSender.enablePlugin(mockPlugin)
                    }

                    verify(exactly = 0) {
                        mockSender.disablePlugin(mockPlugin)
                        mockSender.enableLeavesBreak(mockPlugin)
                        mockSender.disableLeavesBreak(mockPlugin)
                    }
                }
            }

            context("User disable plugin") {
                readyExtensions()
                val args = arrayOf("off")

                should("be called Player.disablePlugin()") {
                    command.onCommand(mockSender, mockCommand, "", args)

                    verify(exactly = 1) {
                        mockSender.disablePlugin(mockPlugin)
                    }

                    verify(exactly = 0) {
                        mockSender.enablePlugin(mockPlugin)
                        mockSender.enableLeavesBreak(mockPlugin)
                        mockSender.disableLeavesBreak(mockPlugin)
                    }
                }
            }
        }

        context("Leaves breaking setting") {
            context("User enable leaves break") {
                readyExtensions()
                val args = arrayOf("leave", "on")

                should("be called Player.enablePlugin()") {
                    command.onCommand(mockSender, mockCommand, "", args)

                    verify(exactly = 1) {
                        mockSender.enableLeavesBreak(mockPlugin)
                    }

                    verify(exactly = 0) {
                        mockSender.disableLeavesBreak(mockPlugin)
                        mockSender.enablePlugin(mockPlugin)
                        mockSender.disablePlugin(mockPlugin)
                    }
                }
            }

            context("User disable leaves break") {
                readyExtensions()
                val args = arrayOf("leave", "off")

                should("be called Player.enablePlugin()") {
                    command.onCommand(mockSender, mockCommand, "", args)

                    verify(exactly = 1) {
                        mockSender.disableLeavesBreak(mockPlugin)
                    }

                    verify(exactly = 0) {
                        mockSender.enableLeavesBreak(mockPlugin)
                        mockSender.enablePlugin(mockPlugin)
                        mockSender.disablePlugin(mockPlugin)
                    }
                }
            }
        }

        context("Version display") {
            readyExtensions()
            every { mockPlugin.description.version }.returns("1.0.0")

            val args = arrayOf("version")

            should("be called Player.sendMessages() with specific string") {
                command.onCommand(mockSender, mockCommand, "", args)

                verify(exactly = 1) {
                    mockSender.sendMessages("[Timber] version: 1.0.0")
                }
            }
        }
    }
}
