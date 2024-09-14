plugins {
	`maven-publish`
	signing
}

publishing {
	publications.withType<MavenPublication> publication@{
		artifact(tasks.register("${name}JavadocJar", Jar::class) {
			archiveClassifier = "javadoc"
			archiveAppendix = this@publication.name
		})

		pom {
			name = "AutomateKt"
			description = "Desktop automation library for Kotlin/Multiplatform"
			url = "https://github.com/Micha-ohne-el/AutomateKt"

			licenses {
				license {
					name = "GPLv3"
					url = "https://github.com/Micha-ohne-el/AutomateKt/blob/main/license.md"
				}
			}
			developers {
				developer {
					id = "Micha-ohne-el"
					name = "Micha"
				}
			}
			scm {
				url = "https://github.com/Micha-ohne-el/AutomateKt"
			}
		}
	}
}

signing {
	if (project.hasProperty("signing.gnupg.keyName")) {
		useGpgCmd()
		sign(publishing.publications)
	}
}
