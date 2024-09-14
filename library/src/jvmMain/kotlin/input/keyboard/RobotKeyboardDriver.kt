package input.keyboard

import java.awt.Robot

class RobotKeyboardDriver(
	private val robot: Robot,
) : KeyboardDriver {
	override suspend fun press(key: Key) {
		robot.keyPress(key.code)
	}

	override suspend fun release(key: Key) {
		robot.keyRelease(key.code)
	}

	override fun close() {}
}
