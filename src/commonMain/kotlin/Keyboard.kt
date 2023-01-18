interface Keyboard {
    fun press(key: Key)

    companion object Primary : Keyboard by PrimaryKeyboard
}

expect object PrimaryKeyboard: Keyboard
