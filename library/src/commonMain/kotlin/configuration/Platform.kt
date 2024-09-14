package configuration

enum class Platform(
	val nativeTarget: Target,
) {
	Linux(Target.Linux),
	Macos(Target.Macos),
	Windows(Target.Windows);
}
