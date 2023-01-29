package keyboard

import java.awt.Robot

object AwtKeyboardDriver : KeyboardDriver {
    override fun press(key: Key) {
        robot.keyPress(key.code)
    }

    override fun release(key: Key) {
        robot.keyRelease(key.code)
    }


    private val robot = Robot()
}
