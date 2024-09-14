import input.keyboard.Key
import input.keyboard.Keyboard
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	Keyboard.fire(Key.H)
	Keyboard.fire(Key.E)
	Keyboard.fire(Key.L)
	Keyboard.fire(Key.L)
	Keyboard.fire(Key.O)
	Keyboard.fire(Key.Space)
	Keyboard.fire(Key.W)
	Keyboard.fire(Key.O)
	Keyboard.fire(Key.R)
	Keyboard.fire(Key.L)
	Keyboard.fire(Key.D)
	Keyboard.press(Key.LeftShift)
	Keyboard.fire(Key.Number1)
	Keyboard.release(Key.LeftShift)
}
