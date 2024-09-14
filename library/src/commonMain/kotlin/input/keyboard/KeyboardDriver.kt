package input.keyboard

interface KeyboardDriver : AutoCloseable {
	suspend fun press(key: Key)
	suspend fun release(key: Key)
}
