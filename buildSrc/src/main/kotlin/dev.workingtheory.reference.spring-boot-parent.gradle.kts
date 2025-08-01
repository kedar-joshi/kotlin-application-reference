import org.jetbrains.kotlin.gradle.dsl.JvmTarget

// Plugins common for sub-modules
plugins {

	// Spring Boot plugin configuration
	id("org.springframework.boot")

	// Spring Boot dependency management configuration
	id("io.spring.dependency-management")

	// Kotlin compiler plugin for JVM
	kotlin("jvm")

	// Kotlin plugin for Spring
	kotlin("plugin.spring")
}

group = "dev.workingtheory.reference"
version = "1.0.0-SNAPSHOT"

// Enabled by 'Java' plugin
java {

	// Java target compatibility
	targetCompatibility = JavaVersion.VERSION_23
}

// Enabled by 'Kotlin' plugin
kotlin {

	// JDK specific toolchain configuration
	jvmToolchain(23)

	compilerOptions {
		freeCompilerArgs.add("-Xjsr305=strict")

		// Kotlin target compatibility
		jvmTarget = JvmTarget.JVM_23
	}
}

// JUnit platform configuration
tasks.withType<Test> {
	useJUnitPlatform()
}

// Dependencies common for sub-modules
dependencies {

	// Logging
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
	implementation("com.lmax:disruptor:3.4.4") // Required by Log4j2 for asynchronous logging

	// Spring framework
	implementation("org.springframework.boot:spring-boot-starter-web") {

		// Disabling default 'logback' logging in favor of Log4j2
		exclude("org.springframework.boot", "spring-boot-starter-logging")

		// Disabling (unused) snakeyaml dependency
		exclude("org.yaml", "snakeyaml")
	}

	// Serialization / deserialization
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
	implementation("com.fasterxml.jackson.module:jackson-module-jakarta-xmlbind-annotations")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5-jakarta")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Database
	implementation("org.postgresql:postgresql")
	implementation("com.zaxxer:HikariCP")
	implementation("org.flywaydb:flyway-core")

	// Persistence
	implementation("org.springframework:spring-orm")
	implementation("org.hibernate.orm:hibernate-hikaricp")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}