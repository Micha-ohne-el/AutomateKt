package configuration

actual object Configuration {
	actual val platform = System.getProperty("os.name").lowercase().let {
		if (it.contains("win")) {
			Platform.Windows
		} else if (it.contains("mac")) {
			Platform.Macos
		} else if (it.contains("linux")) {
			Platform.Linux
		} else {
			throw RuntimeException("Unsupported platform: $it")
		}
	}
	actual val target = Target.Jvm
}
