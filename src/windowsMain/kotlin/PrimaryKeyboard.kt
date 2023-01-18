import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import platform.windows.INPUT
import kotlinx.cinterop.*
import platform.windows.*

actual object PrimaryKeyboard : Keyboard {
    override fun press(key: Key): Unit = memScoped {
        val input = alloc<INPUT> {
            type = INPUT_KEYBOARD.toUInt()
            ki.apply {
                wVk = key.virtualKeyCode
            }
        }

        SendInput(1, input.ptr, sizeOf<INPUT>().toInt())
    }
}
