import input.keyboard.Key
import input.keyboard.primaryKeyboard
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	primaryKeyboard.fire(Key.H)
	primaryKeyboard.fire(Key.E)
	primaryKeyboard.fire(Key.L)
	primaryKeyboard.fire(Key.L)
	primaryKeyboard.fire(Key.O)
	primaryKeyboard.fire(Key.Space)
	primaryKeyboard.fire(Key.W)
	primaryKeyboard.fire(Key.O)
	primaryKeyboard.fire(Key.R)
	primaryKeyboard.fire(Key.L)
	primaryKeyboard.fire(Key.D)
	primaryKeyboard.press(Key.LeftShift)
	primaryKeyboard.fire(Key.Number1)
	primaryKeyboard.release(Key.LeftShift)
}
