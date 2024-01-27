import org.jetbrains.kotlin.gradle.dsl.JvmTarget

description = "kotlin-application-parent"

// Convention plugin configuration
plugins {
	// Enables convention definitions in src/main/kotlin
	`kotlin-dsl`
}

// Java compiler configuration
java {

	/**
	 * Configuring Java target compatibility.
	 *
	 * Although Kotlin compiler options are sufficient for Gradle,
	 * IntelliJ often complains if this Java target compatibility is not explicitly set.
	 */
	targetCompatibility = JavaVersion.VERSION_21
}

// Kotlin compiler configuration
kotlin {

	// JDK specific toolchain configuration
	jvmToolchain(21)

	compilerOptions {

		jvmTarget = JvmTarget.JVM_21
	}
}

// 'buildSrc' specific dependencies
dependencies {

	// Kotlin
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
	implementation("org.jetbrains.kotlin:kotlin-allopen:1.9.22")

	// Spring Boot
	implementation("org.springframework.boot:spring-boot-gradle-plugin:3.1.3")
}