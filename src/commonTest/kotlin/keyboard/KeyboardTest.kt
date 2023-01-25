package keyboard

import kotlin.test.Test
import kotlin.test.assertContentEquals

class KeyboardTest {
    @Test
    fun `press calls press on driver`() {
        val driver = SpyDriver()
        val sut = Keyboard(driver)

        sut.press(Key.A)

        assertContentEquals(listOf(Key.A), driver.presses)
        assertContentEquals(emptyList(), driver.releases)
    }

    @Test
    fun `press calls press on driver multiple times`() {
        val driver = SpyDriver()
        val sut = Keyboard(driver)

        sut.press(Key.A)
        sut.press(Key.B)

        assertContentEquals(listOf(Key.A, Key.B), driver.presses)
        assertContentEquals(emptyList(), driver.releases)
    }

    @Test
    fun `release calls release on driver`() {
        val driver = SpyDriver()
        val sut = Keyboard(driver)

        sut.release(Key.A)

        assertContentEquals(emptyList(), driver.presses)
        assertContentEquals(listOf(Key.A), driver.releases)
    }

    @Test
    fun `release calls release on driver multiple times`() {
        val driver = SpyDriver()
        val sut = Keyboard(driver)

        sut.release(Key.A)
        sut.release(Key.B)

        assertContentEquals(emptyList(), driver.presses)
        assertContentEquals(listOf(Key.A, Key.B), driver.releases)
    }

    @Test
    fun `fire calls press and release on driver`() {
        val driver = SpyDriver()
        val sut = Keyboard(driver)

        sut.fire(Key.A)

        assertContentEquals(listOf(Key.A), driver.presses)
        assertContentEquals(listOf(Key.A), driver.releases)
    }

    @Test
    fun `fire calls press and release on driver in correct order`() {
        val driver = SpyDriver()
        val sut = Keyboard(driver)

        sut.fire(Key.A)
        sut.fire(Key.B)

        assertContentEquals(
            listOf(
                SpyDriver.Event(Key.A, down = true),
                SpyDriver.Event(Key.A, down = false),
                SpyDriver.Event(Key.B, down = true),
                SpyDriver.Event(Key.B, down = false)
            ),
            driver.events
        )
    }

    private class SpyDriver : KeyboardDriver {
        val presses = mutableListOf<Key>()
        val releases = mutableListOf<Key>()
        val events = mutableListOf<Event>()

        override fun press(key: Key) {
            presses += key
            events += Event(key, down = true)
        }

        override fun release(key: Key) {
            releases += key
            events += Event(key, down = false)
        }

        data class Event(
            val key: Key,
            val down: Boolean
        )
    }
}
