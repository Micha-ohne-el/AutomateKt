import kotlin.test.Test

class KeyboardTest {
    @Test
    fun `press presses down a key`() {
        Keyboard.press(Key.T)
        Keyboard.press(Key.H)
        Keyboard.press(Key.I)
        Keyboard.press(Key.S)

        Keyboard.press(Key.Space)

        Keyboard.press(Key.I)
        Keyboard.press(Key.S)

        Keyboard.press(Key.Space)

        Keyboard.press(Key.A)

        Keyboard.press(Key.Space)

        Keyboard.press(Key.T)
        Keyboard.press(Key.E)
        Keyboard.press(Key.S)
        Keyboard.press(Key.T)
    }
}
