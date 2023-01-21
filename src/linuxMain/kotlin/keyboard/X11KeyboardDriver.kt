package keyboard

import kotlinx.cinterop.CValuesRef
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import platform.x11.*

object X11KeyboardDriver : KeyboardDriver {
    override fun press(key: Key) {
        memScoped {
            val event = alloc<XKeyEvent> {
                keycode = key.keyCode
            }.reinterpret<XEvent>().ptr

            XSendEvent(
                display = display,
                window = InputFocus.toULong(),
                propagate = false,
                eventMask = 0u,
                event = event
            )
        }
    }

    override fun release(key: Key) {
    }


    private val display by lazy {
        XOpenDisplay(null) ?: throw IllegalStateException("Cannot connect to XDisplay.")
    }
}

private inline fun XSendEvent(
    display: CValuesRef<Display>,
    window: Window,
    propagate: Boolean,
    eventMask: Mask,
    event: CValuesRef<XEvent>,
) = XSendEvent(display, window, if (propagate) 1 else 0, eventMask.toLong(), event)
