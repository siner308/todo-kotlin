import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.4")
    implementation("org.springframework.boot:spring-boot-starter:3.1.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.4")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.10")
    implementation("org.projectlombok:lombok:1.18.30")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.4")
    implementation("mysql:mysql-connector-java:8.0.33")
//    implementation("org.javassist:javassist:3.29.2-GA")

    // test
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.4")
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    testImplementation("io.kotest:kotest-assertions-core:5.7.2")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
    }
}