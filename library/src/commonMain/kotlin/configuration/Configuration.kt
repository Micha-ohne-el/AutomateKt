package configuration

expect object Configuration {
	val platform: Platform
	val target: Target
}

val Configuration.isRunningNatively get() = platform.nativeTarget == target
val Configuration.isRunningOnJvm get() = target == Target.Jvm
