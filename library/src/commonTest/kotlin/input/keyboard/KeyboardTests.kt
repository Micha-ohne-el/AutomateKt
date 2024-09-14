package input.keyboard

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class KeyboardTests : DescribeSpec() {
	init {
		describe("press") {
			it("calls driver.press") {
				val driver = KeyboardDriverSpy()
				val keyboard = Keyboard(driver)

				driver.events.shouldBeEmpty()

				keyboard.press(Key.A)

				driver.events.size shouldBe 1
				driver.events.last() shouldBe KeyboardDriverSpy.Event(Key.A, true)

				keyboard.press(Key.B)

				driver.events.size shouldBe 2
				driver.events.last() shouldBe KeyboardDriverSpy.Event(Key.B, true)
			}
		}

		describe("release") {
			it("calls driver.release") {
				val driver = KeyboardDriverSpy()
				val keyboard = Keyboard(driver)

				driver.events.shouldBeEmpty()

				keyboard.release(Key.A)

				driver.events.size shouldBe 1
				driver.events.last() shouldBe KeyboardDriverSpy.Event(Key.A, false)

				keyboard.release(Key.B)

				driver.events.size shouldBe 2
				driver.events.last() shouldBe KeyboardDriverSpy.Event(Key.B, false)
			}
		}

		describe("fire") {
			it("calls driver.press and driver.release") {
				val driver = KeyboardDriverSpy()
				val keyboard = Keyboard(driver)

				driver.events.shouldBeEmpty()

				keyboard.fire(Key.A)

				driver.events.size shouldBe 2
				driver.events.first() shouldBe KeyboardDriverSpy.Event(Key.A, true)
				driver.events.last() shouldBe KeyboardDriverSpy.Event(Key.A, false)

				keyboard.fire(Key.B)

				driver.events.size shouldBe 4
				driver.events.drop(2).first() shouldBe KeyboardDriverSpy.Event(Key.B, true)
				driver.events.drop(2).last() shouldBe KeyboardDriverSpy.Event(Key.B, false)
			}
		}
	}

	private class KeyboardDriverSpy : KeyboardDriver {
		val events = mutableListOf<Event>()

		override suspend fun press(key: Key) {
			events += Event(key, true)
		}

		override suspend fun release(key: Key) {
			events += Event(key, false)
		}

		override fun close() {}

		data class Event(
			val key: Key,
			val pressed: Boolean,
		)
	}
}
