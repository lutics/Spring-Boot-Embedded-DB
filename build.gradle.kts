plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
//    id("org.springframework.boot") version "3.4.1"
//    id("org.springframework.boot") version "3.0.1"
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
//        languageVersion = JavaLanguageVersion.of(21) // 3.4.1
        languageVersion = JavaLanguageVersion.of(19) // 3.0.1 or 2.7.1
    }
}

repositories {
    mavenCentral()
}

// 3.4.1 일 경우 삭제
dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:1.20.4")
    }
}

dependencies {

    // Test
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")

    implementation("org.springframework.boot:spring-boot-starter-test")
//    implementation("org.springframework.boot:spring-boot-testcontainers") // 3.4.1 일 경우 dependencyManagement 대신 추가
    implementation("org.testcontainers:junit-jupiter")
    implementation("org.testcontainers:mongodb")
    implementation("org.testcontainers:mysql")
    implementation("com.redis:testcontainers-redis:2.2.2")

    // Mac 환경에서 2.7.1 Webflux(Netty) 실행 시 추가
    implementation("io.netty:netty-resolver-dns-native-macos:4.1.104.Final:osx-aarch_64")

    // 생성 시 자동으로 들어 있는거
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("io.projectreactor:reactor-test")
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
