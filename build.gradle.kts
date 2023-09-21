import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath("org.flywaydb:flyway-mysql:9.22.0")
  }
}

plugins {
  id("org.springframework.boot") version "3.1.3"
  id("io.spring.dependency-management") version "1.1.3"
  id("org.flywaydb.flyway") version "9.22.0"
  id("org.jlleitschuh.gradle.ktlint") version "11.6.0"
  id("io.gitlab.arturbosch.detekt") version "1.23.1"
  kotlin("jvm") version "1.9.0"
  kotlin("kapt") version "1.9.0"
  kotlin("plugin.spring") version "1.9.0"
}

group = "com.hong"
version = "0.0.1-SNAPSHOT"

val kotestVersion by extra("5.6.2")
val mapStructVersion by extra(
  "1.5.2.Final"
)
java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  implementation("org.mapstruct:mapstruct:$mapStructVersion")
  kapt("org.mapstruct:mapstruct-processor:$mapStructVersion")
  kaptTest("org.mapstruct:mapstruct-processor:$mapStructVersion")

  runtimeOnly("com.mysql:mysql-connector-j")
  implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
  kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
  testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
  testImplementation("io.kotest:kotest-extensions-spring:4.4.3")

  testImplementation("org.flywaydb:flyway-mysql:9.22.0")
  testImplementation("org.flywaydb:flyway-core")
  testImplementation("org.testcontainers:mysql:1.16.0")
  testImplementation("io.kotest.extensions:kotest-extensions-testcontainers:2.0.2")

  detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

val testIntegration: SourceSet by sourceSets.creating {
  compileClasspath += sourceSets.main.get().output
  runtimeClasspath += sourceSets.main.get().output
}

configurations {
  testIntegration.implementationConfigurationName {
    extendsFrom(configurations.testImplementation.get())
  }

  testIntegration.runtimeOnlyConfigurationName {
    extendsFrom(configurations.testRuntimeOnly.get())
  }
}

val testIntegrationTask = tasks.register<Test>("testIntegration") {
  description = "Runs integration tests."
  group = "verification"
  useJUnitPlatform()

  testClassesDirs = testIntegration.output.classesDirs
  classpath = testIntegration.runtimeClasspath

  shouldRunAfter(tasks.test)
}

tasks.check {
  dependsOn(testIntegrationTask)
}

detekt {
  buildUponDefaultConfig = true
  allRules = false
  config.setFrom(files("$rootDir/config/detekt.yml"))
}

flyway {
  url = "jdbc:mysql://localhost:3306/samplestore"
  user = "root"
  password = "12345"
  locations = arrayOf("filesystem:${file("src/migration").absolutePath}")
  encoding = "UTF-8"
  cleanDisabled = false
  validateOnMigrate = true
}
