package input.keyboard

open class Keyboard(
	protected val driver: KeyboardDriver,
) : AutoCloseable {
	suspend fun press(key: Key) = driver.press(key)

	suspend fun release(key: Key) = driver.release(key)

	suspend fun fire(key: Key) {
		press(key)
		release(key)
	}

	override fun close() {
		driver.close()
	}
}
