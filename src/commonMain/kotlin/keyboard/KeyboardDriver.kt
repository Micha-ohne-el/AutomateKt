package keyboard

interface KeyboardDriver {
	fun press(key: Key)

	fun release(key: Key)
}
