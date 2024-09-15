package input.keyboard

import dev.mokkery.MockMode.autoUnit
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.exhaustiveOrder
import dev.mokkery.verify.VerifyMode.Companion.not
import dev.mokkery.verifySuspend
import io.kotest.core.spec.style.DescribeSpec

class KeyboardTests : DescribeSpec() {
	init {
		describe("press") {
			it("calls driver.press") {
				val driver = mock<KeyboardDriver>(autoUnit)
				val keyboard = Keyboard(driver)

				verifySuspend(not) { driver.press(any()) }

				keyboard.press(Key.A)

				verifySuspend { driver.press(Key.A) }

				keyboard.press(Key.B)

				verifySuspend { driver.press(Key.B) }
			}
		}

		describe("release") {
			it("calls driver.release") {
				val driver = mock<KeyboardDriver>(autoUnit)
				val keyboard = Keyboard(driver)

				verifySuspend(not) { driver.release(any()) }

				keyboard.release(Key.A)

				verifySuspend { driver.release(Key.A) }

				keyboard.release(Key.B)

				verifySuspend { driver.release(Key.B) }
			}
		}

		describe("fire") {
			it("calls driver.press and driver.release") {
				val driver = mock<KeyboardDriver>(autoUnit)
				val keyboard = Keyboard(driver)

				verifySuspend(not) { driver.press(any()) }
				verifySuspend(not) { driver.release(any()) }

				keyboard.fire(Key.A)

				verifySuspend(exhaustiveOrder) {
					driver.press(Key.A)
					driver.release(Key.A)
				}

				keyboard.fire(Key.B)

				verifySuspend(exhaustiveOrder) {
					driver.press(Key.B)
					driver.release(Key.B)
				}
			}
		}
	}
}
