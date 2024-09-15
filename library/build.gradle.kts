import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.kotest)
	alias(libs.plugins.mokkery)
	id("module.publication")
}

kotlin {
	jvm {
		testRuns.all {
			executionTask {
				useJUnitPlatform()
			}
		}
	}
	linuxX64 {
		compilations.named("main") {
			cinterops.register("input")
			cinterops.register("uinput")
		}
	}
	mingwX64()

	@OptIn(ExperimentalKotlinGradlePluginApi::class)
	compilerOptions {
		freeCompilerArgs.add("-Xexpect-actual-classes")
		allWarningsAsErrors = true
	}

	sourceSets {
		commonMain {
			dependencies {
				implementation(libs.kotlinx.coroutines.core)
			}
		}

		commonTest {
			dependencies {
				implementation(libs.kotest.framework)
				implementation(libs.kotest.assertions)
			}
		}

		jvmTest {
			dependencies {
				implementation(libs.kotest.runner.junit5)
			}
		}
	}
}
