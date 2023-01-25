package keyboard

open class Keyboard(
    private val driver: KeyboardDriver
) {
    fun press(key: Key) = driver.press(key)

    fun release(key: Key) = driver.release(key)

    fun fire(key: Key) {
        driver.press(key)
        driver.release(key)
    }

    companion object Primary : Keyboard(primaryKeyboardDriver)
}
