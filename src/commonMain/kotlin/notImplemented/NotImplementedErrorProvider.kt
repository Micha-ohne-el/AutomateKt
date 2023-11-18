package notImplemented

import kotlin.reflect.KProperty

abstract class NotImplementedErrorProvider(
	private val errorMessage: String,
) {
	operator fun getValue(thisRef: Any, property: KProperty<*>): Nothing {
		throw NotImplementedError(errorMessage)
	}

	operator fun invoke(): Nothing {
		throw NotImplementedError(errorMessage)
	}
}
