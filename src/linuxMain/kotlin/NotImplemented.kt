import kotlin.reflect.KProperty

actual object NotYetImplementedForThisPlatform {
    actual operator fun getValue(thisRef: Any, property: KProperty<*>): Nothing {
        throw NotImplementedError("This has not yet been implemented for the Linux platform, sorry.")
    }

    actual operator fun invoke(): Nothing {
        throw NotImplementedError("This has not yet been implemented for the Linux platform, sorry.")
    }
}

actual object NotApplicableForThisPlatform {
    actual operator fun getValue(thisRef: Any, property: KProperty<*>): Nothing {
        throw NotImplementedError("This will not be implemented for the Linux platform.")
    }

    actual operator fun invoke(): Nothing {
        throw NotImplementedError("This will not be implemented for the Linux platform.")
    }
}
