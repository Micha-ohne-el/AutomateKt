interface Keyboard {
    fun press(key: Key)

    companion object Primary : Keyboard by primaryKeyboard
}

expect val primaryKeyboard: Keyboard
