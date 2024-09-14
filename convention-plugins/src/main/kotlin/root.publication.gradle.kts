plugins {
	id("io.github.gradle-nexus.publish-plugin")
}

allprojects {
	group = "moe.micha"
	version = "0.0.1"
}

nexusPublishing {
	repositories {
		sonatype {
			nexusUrl = uri("https://s01.oss.sonatype.org/service/local/")
			snapshotRepositoryUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
		}
	}
}
