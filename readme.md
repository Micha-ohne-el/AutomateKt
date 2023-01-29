# AutomateKt
Desktop Automation in Kotlin

## Development Setup
### Prerequisites
*   A Kotlin-capable IDE, such as [IntelliJ IDEA](https://jetbrains.com/idea)
*   The Kotlin/Native compiler
    *   This is available from Kotlin's [official GitHub releases](https://github.com/JetBrains/kotlin/releases),
        or your favorite Linux Distro's package manager (probably).

### Steps
*   Clone the repo
*   Build the project with `./gradlew build` (on Windows it's `gradlew.bat`)

### Architecture
#### Drivers
As one of the goals of this project is maximum stability, everything must be covered by well-written, clean, automated tests.
To achieve optimal testability in this rather IO-focused project, we use what we call “Drivers”.
They are tiny classes that provide basic functionality for interacting with the hardware.
They should be simple enough that testing is not required – you should be able to tell at a glance that they are correct.
The real logic should be in separate classes which use such drivers.
These must be fully tested, as they provide logic that makes using them painless compared to the drivers.  
For a good example, see `commonMain/keyboard/Keyboard.kt` and `commonMain/keyboard/KeyboardDriver.kt`,
as well as `commonTest/keyboard/KeyboardTest.kt` for the tests.
