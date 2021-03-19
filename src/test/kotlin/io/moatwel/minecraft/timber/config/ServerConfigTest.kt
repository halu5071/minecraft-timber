package io.moatwel.minecraft.timber.config

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.bukkit.plugin.java.JavaPlugin

class ServerConfigTest : ShouldSpec() {

    init {
        context("getXZBreakRangeLimit") {
            context("specified under default value (5)") {
                val mockPlugin = mockk<JavaPlugin>().apply {
                    every { config.getInt(any()) }.returns(4)
                }
                val config = ServerConfig(mockPlugin)

                should("return default value (5)") {
                    val rangeLimit = config.getXZBreakRangeLimit()

                    rangeLimit shouldNotBe 4
                    rangeLimit shouldBe 5
                }
            }

            context("specified over default value") {
                val mockPlugin = mockk<JavaPlugin>().apply {
                    every { config.getInt(any()) }.returns(10)
                }
                val config = ServerConfig(mockPlugin)

                should("return specified value") {
                    val rangeLimit = config.getXZBreakRangeLimit()

                    rangeLimit shouldBe 10
                }
            }
        }
    }
}
