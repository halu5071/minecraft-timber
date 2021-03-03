package io.moatwel.minecraft.timber

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class BooleanTest : ShouldSpec() {

    init {
        should("Boolean 1") {
            var shouldAccept = false

            shouldAccept = shouldAccept && true

            shouldAccept shouldBe false
        }

        should("Boolean 2") {
            var shouldAccept = false

            shouldAccept = shouldAccept && false

            shouldAccept shouldBe false
        }

        should("Boolean 3") {
            var shouldAccept = true

            shouldAccept = shouldAccept && true

            shouldAccept shouldBe true
        }

        should("Boolean 4") {
            var shouldAccept = true

            shouldAccept = shouldAccept && false

            shouldAccept shouldBe false
        }
    }
}