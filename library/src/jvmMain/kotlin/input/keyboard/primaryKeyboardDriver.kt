package input.keyboard

import java.awt.Robot

actual val primaryKeyboardDriver: KeyboardDriver by lazy { RobotKeyboardDriver(Robot()) }
