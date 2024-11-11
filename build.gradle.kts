import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.google.cloud.tools.jib") version "3.4.4"
    id("jacoco")
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.spring") version "2.0.21"
    kotlin("plugin.jpa") version "2.0.21"
}

group = "dev.stonegarden"
version = "0.0.2-SNAPSHOT"

val javaVersion = JavaVersion.VERSION_21

tasks.register("printVersion") {
    doLast {
        println(project.version)
    }
}

java {
    sourceCompatibility = javaVersion
}

repositories {
    mavenCentral()
}

jib {
    from {
        image = "gcr.io/distroless/java${javaVersion.majorVersion}-debian12:nonroot"
        platforms {
            platform {
                architecture = "amd64"
                os = "linux"
            }
            platform {
                architecture = "arm64"
                os = "linux"
            }
        }
    }
}

dependencies {
    val mockkVersion = "1.13.13"
    val h2Version = "2.3.232"
    val flywayVersion = "10.21.0"
    val openapiVersion = "2.6.0"

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${openapiVersion}")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.flywaydb:flyway-core:${flywayVersion}")
    implementation("org.flywaydb:flyway-database-postgresql:${flywayVersion}")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2:${h2Version}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    testImplementation("org.flywaydb:flyway-core:${flywayVersion}")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.mockk:mockk:${mockkVersion}")
    testRuntimeOnly("com.h2database:h2:${h2Version}")

    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")

    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
