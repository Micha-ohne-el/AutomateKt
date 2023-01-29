package keyboard

import kotlin.test.Test
import kotlin.test.assertContentEquals

class KeyboardTest {
    class Press {
        @Test
        fun `press calls press on driver`() {
            val driver = SpyDriver()
            val sut = Keyboard(driver)

            sut.press(Key.A)

            assertContentEquals(listOf(Key.A to true), driver.stateChanges)
        }

        @Test
        fun `press calls press on driver multiple times`() {
            val driver = SpyDriver()
            val sut = Keyboard(driver)

            sut.press(Key.A)
            sut.press(Key.B)

            assertContentEquals(listOf(Key.A to true, Key.B to true), driver.stateChanges)
        }
    }

    class Release {
        @Test
        fun `release calls release on driver`() {
            val driver = SpyDriver()
            val sut = Keyboard(driver)

            sut.release(Key.A)

            assertContentEquals(listOf(Key.A to false), driver.stateChanges)
        }

        @Test
        fun `release calls release on driver multiple times`() {
            val driver = SpyDriver()
            val sut = Keyboard(driver)

            sut.release(Key.A)
            sut.release(Key.B)

            assertContentEquals(listOf(Key.A to false, Key.B to false), driver.stateChanges)
        }
    }

    class Fire {
        @Test
        fun `fire calls press and release on driver`() {
            val driver = SpyDriver()
            val sut = Keyboard(driver)

            sut.fire(Key.A)

            assertContentEquals(listOf(Key.A to true, Key.A to false), driver.stateChanges)
        }

        @Test
        fun `fire calls press and release on driver in correct order`() {
            val driver = SpyDriver()
            val sut = Keyboard(driver)

            sut.fire(Key.A)
            sut.fire(Key.B)

            assertContentEquals(
                listOf(
                    Key.A to true,
                    Key.A to false,
                    Key.B to true,
                    Key.B to false
                ),
                driver.stateChanges
            )
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
