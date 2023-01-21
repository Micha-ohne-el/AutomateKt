plugins {
    kotlin("multiplatform") version "1.7.20"
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
kotlin {
    linuxX64()
    macosX64()
    macosArm64()
    mingwX64("windowsX64")
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
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
                compileOnly(files("libs/x11.klib"))
            }
        }
        val linuxX64Main by getting { dependsOn(linuxMain) }

        val macosMain by creating { dependsOn(nativeMain) }
        val macosX64Main by getting { dependsOn(macosMain) }
        val macosArm64Main by getting { dependsOn(macosMain) }

        val windowsMain by creating {dependsOn(nativeMain) }
        val windowsX64Main by getting { dependsOn(windowsMain) }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))

                implementation("com.squareup.okio:okio-fakefilesystem:3.3.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
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
