package keyboard

import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.sizeOf
import platform.windows.INPUT
import platform.windows.INPUT_KEYBOARD
import platform.windows.KEYEVENTF_KEYUP
import platform.windows.SendInput

object SendInputKeyboardDriver : KeyboardDriver {
    override fun press(key: Key) {
        sendInput(Input(key, down = true))
    }

    override fun release(key: Key) {
        sendInput(Input(key, down = false))
    }


    private fun sendInput(vararg inputs: Input) = memScoped {
        val inputsArray = allocArray<INPUT>(inputs.size)

        for (i in inputs.indices) {
            inputsArray[i].type = INPUT_KEYBOARD.toUInt()
            inputsArray[i].ki.apply {
                wVk = inputs[i].key.virtualKeyCode
                if (!inputs[i].down) {
                    dwFlags += KEYEVENTF_KEYUP.toUInt()
                }
            }
        }

        SendInput(inputs.size.toUInt(), inputsArray.pointed.ptr, sizeOf<INPUT>().toInt())
    }

    private data class Input(
        val key: Key,
        val down: Boolean,
    )
}
