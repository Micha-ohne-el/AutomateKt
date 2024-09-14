package input.keyboard

import kotlin.time.Duration.Companion.milliseconds
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.sizeOf
import kotlinx.cinterop.toKString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import platform.linux.input.BUS_VIRTUAL
import platform.linux.input.EV_KEY
import platform.linux.input.EV_SYN
import platform.linux.input.SYN_REPORT
import platform.linux.input.input_event
import platform.linux.uinput.UI_DEV_CREATE
import platform.linux.uinput.UI_DEV_DESTROY
import platform.linux.uinput.UI_DEV_SETUP
import platform.linux.uinput.UI_SET_EVBIT
import platform.linux.uinput.UI_SET_KEYBIT
import platform.linux.uinput.uinput_setup
import platform.posix.*

@OptIn(ExperimentalForeignApi::class)
class UInputKeyboardDriver : KeyboardDriver {
	override suspend fun press(key: Key) = sendKeyEvent(key, true)

	override suspend fun release(key: Key) = sendKeyEvent(key, false)

	override fun close() {
		ioctl(fd, UI_DEV_DESTROY.convert())
		close(fd)
	}


	private var setup: Job

	private suspend fun sendKeyEvent(key: Key, pressed: Boolean) {
		sendEvent(EV_KEY, key.code, if (pressed) 1 else 0)
		sendEvent(EV_SYN, SYN_REPORT, 0)
	}

	private suspend fun sendEvent(type: Int, code: Int, value: Int) = coroutineScope {
		setup.join()

		memScoped {
			val event = alloc<input_event>()

			event.type = type.convert()
			event.code = code.convert()
			event.value = value
			event.time.tv_sec = 0
			event.time.tv_usec = 0

			write(fd, event.ptr, sizeOf<input_event>().convert())
		}
	}

	private val fd = open("/dev/uinput", O_WRONLY or O_NONBLOCK).also { fd ->
		when (fd) {
			-EACCES -> error("Could not open /dev/uinput due to lack of permissions.")
			-EISDIR -> error("Could not open /dev/uinput because it's a directory.")
			else -> if (fd < 0) error("Could not open /dev/uinput due to some unexpected error (${strerror(-fd)?.toKString() ?: -fd}).")
		}
	}

	init {
		runBlocking(Dispatchers.IO) {
			setup = launch {
				ioctl(fd, UI_SET_EVBIT, EV_KEY)
				ioctl(fd, UI_SET_EVBIT, EV_SYN)

				for (key in Key.entries) {
					ioctl(fd, UI_SET_KEYBIT, key.code)
				}

				memScoped {
					val setup = alloc<uinput_setup>()
					setup.id.bustype = BUS_VIRTUAL.convert()
					setup.id.vendor = 0xdf5u // Just some randomly chosen numbers
					setup.id.product = 0xc4ceu // The signature of this virtual keyboard, basically
					strcpy(setup.name, "AutomateKt Virtual Keyboard")

					ioctl(fd, UI_DEV_SETUP, setup.ptr)
					ioctl(fd, UI_DEV_CREATE.convert())
				}

				// The kernel takes time to initialize the device, which there is no way (that I know of) to wait precisely for.
				// The setup job will finish after 100 ms and all events must wait until that delay has passed.
				// This is really suboptimal and needs improvement if possible.
				delay(100.milliseconds)
			}
		}
	}
}
