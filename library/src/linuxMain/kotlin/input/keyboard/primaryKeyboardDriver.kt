package input.keyboard

actual val primaryKeyboardDriver: KeyboardDriver by lazy { UInputKeyboardDriver() }
