import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
	alias(libs.plugins.kotlinMultiplatform)
	id("module.publication")
}

kotlin {
	jvm {
		@OptIn(ExperimentalKotlinGradlePluginApi::class)
		mainRun {
			mainClass = "MainKt"
		}
	}
	linuxX64()
	mingwX64()

	targets.withType<KotlinNativeTarget> {
		binaries {
			executable()
		}
	}

	@OptIn(ExperimentalKotlinGradlePluginApi::class)
	compilerOptions {
		allWarningsAsErrors = true
	}

	sourceSets {
		commonMain {
			dependencies {
				implementation(project(":library"))
				implementation(libs.kotlinx.coroutines.core)
			}
		}
	}
}
