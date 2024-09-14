package input.keyboard

object NoopKeyboardDriver : KeyboardDriver {
	override suspend fun press(key: Key) {}

	override suspend fun release(key: Key) {}

	override fun close() {}
}
