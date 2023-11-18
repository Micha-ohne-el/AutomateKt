package keyboard

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class KeyboardTest : DescribeSpec() {
	init {
		describe("press") {
			it("calls press on driver") {
				val driver = SpyDriver()
				val sut = Keyboard(driver)

				sut.press(Key.A)

				driver.stateChanges shouldBe listOf(Key.A to true)
			}

			it("calls press on driver multiple times") {
				val driver = SpyDriver()
				val sut = Keyboard(driver)

				sut.press(Key.A)
				sut.press(Key.B)

				driver.stateChanges shouldBe listOf(Key.A to true, Key.B to true)
			}
		}

		describe("release") {
			it("calls release on driver") {
				val driver = SpyDriver()
				val sut = Keyboard(driver)

				sut.release(Key.A)

				driver.stateChanges shouldBe listOf(Key.A to false)
			}

			it("calls release on driver multiple times") {
				val driver = SpyDriver()
				val sut = Keyboard(driver)

				sut.release(Key.A)
				sut.release(Key.B)

				driver.stateChanges shouldBe listOf(Key.A to false, Key.B to false)
			}
		}

		describe("fire") {
			it("calls press and release on driver") {
				val driver = SpyDriver()
				val sut = Keyboard(driver)

				sut.fire(Key.A)

				driver.stateChanges shouldBe listOf(Key.A to true, Key.A to false)
			}

			it("calls press and release on driver in correct order") {
				val driver = SpyDriver()
				val sut = Keyboard(driver)

				sut.fire(Key.A)
				sut.fire(Key.B)

				driver.stateChanges shouldBe listOf(
					Key.A to true, Key.A to false, Key.B to true, Key.B to false
				)
			}
		}
	}


	private class SpyDriver : KeyboardDriver {
		val stateChanges = mutableListOf<Pair<Key, Boolean>>()

		override fun press(key: Key) {
			stateChanges += key to true
		}

		override fun release(key: Key) {
			stateChanges += key to false
		}
	}
}
