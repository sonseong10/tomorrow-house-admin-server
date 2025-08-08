plugins {
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.tomorrow-house-admin-server"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot Web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Spring Security
	implementation("org.springframework.boot:spring-boot-starter-security")

	// JPA + DB Driver (H2 예시)
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")

	// Redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	// JWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	// Validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Password Encoder (BCrypt)
	implementation("org.springframework.security:spring-security-crypto")

	// Swagger (Springdoc OpenAPI)
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

	// Kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}



kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
