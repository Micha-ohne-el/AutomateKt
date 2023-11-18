plugins {
	kotlin("multiplatform") version "1.8.0"

	id("io.kotest.multiplatform") version "5.8.0"
}

group = "moe.micha"
version = "0.0.0"

repositories {
	mavenCentral()
}

/*
	Project structure looks like this:
	• common
		• jvm
		• native
			• linux
				• linuxX64
			• macos
				• macosX64
				• macosArm64
			• windows
				• windowsX64

	Each target has both Main and Test source sets.
*/

object KlibPaths {
	lateinit var x11: File
}

kotlin {
	linuxX64 {
		compilations.getByName("main") {
			cinterops.create("X11") {
				defFile(file("src/linuxMain/resources/x11.def"))
			}
			KlibPaths.x11 = output.resourcesDir / "cinterop" / "AutomateKt-cinterop-X11.klib"
		}
	}
	macosX64()
	macosArm64()
	mingwX64("windowsX64")
	jvm {
		withJava()
		compilations.all {
			kotlinOptions.jvmTarget = "1.8"
		}

		testRuns.all {
			executionTask {
				useJUnitPlatform()
			}
		}
	}

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation("com.squareup.okio:okio:3.3.0")
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
			}
		}

		val jvmMain by getting { dependsOn(commonMain) }

		val nativeMain by creating { dependsOn(commonMain) }

		val linuxMain by creating {
			dependsOn(nativeMain)

			dependencies {
				compileOnly(files(KlibPaths.x11))
			}
		}
		val linuxX64Main by getting { dependsOn(linuxMain) }

		val macosMain by creating { dependsOn(nativeMain) }
		val macosX64Main by getting { dependsOn(macosMain) }
		val macosArm64Main by getting { dependsOn(macosMain) }

		val windowsMain by creating { dependsOn(nativeMain) }
		val windowsX64Main by getting { dependsOn(windowsMain) }

		val commonTest by getting {
			dependencies {
				implementation("io.kotest:kotest-framework-engine:5.8.0")
				implementation("io.kotest:kotest-assertions-core:5.8.0")
				implementation("io.kotest:kotest-property:5.8.0")

				implementation("com.squareup.okio:okio-fakefilesystem:3.3.0")
				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
			}
		}

		val jvmTest by getting {
			dependsOn(commonTest)

			dependencies {
				implementation("io.kotest:kotest-runner-junit5:5.8.0")
			}
		}

		val nativeTest by creating { dependsOn(commonTest) }

		val linuxTest by creating { dependsOn(nativeTest) }
		val linuxX64Test by getting { dependsOn(linuxTest) }

		val macosTest by creating { dependsOn(nativeTest) }
		val macosX64Test by getting { dependsOn(macosTest) }
		val macosArm64Test by getting { dependsOn(macosTest) }

		val windowsTest by creating { dependsOn(nativeTest) }
		val windowsX64Test by getting { dependsOn(windowsTest) }
	}
}


operator fun File.div(other: String) = resolve(other)
