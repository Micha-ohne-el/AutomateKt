import kotlin.reflect.KProperty

expect object NotYetImplementedForThisPlatform {
	operator fun getValue(thisRef: Any, property: KProperty<*>): Nothing

	operator fun invoke(): Nothing
}

expect object NotApplicableForThisPlatform {
	operator fun getValue(thisRef: Any, property: KProperty<*>): Nothing

	operator fun invoke(): Nothing
}
