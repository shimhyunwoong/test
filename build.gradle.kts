import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.4.32"
    id("org.springframework.boot") version "2.4.13"
    // 새로운 ktlint gradle plugin
    // kotlin 버전 혹은 gradle-wrapper 버전을 올릴 경우 아래 링크를 통해 호환성을 참조해주세요
    // https://github.com/jeremymailen/kotlinter-gradle#compatibility
    id("org.jmailen.kotlinter") version "3.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

// change this
group = "com.htbeyond.mid"
version = System.getenv("APP_VERSION") ?: "none"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    val querydslVersion = "5.0.0"
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    //springSecurity
    testImplementation("org.springframework.security:spring-security-test")

    //JWT
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation("commons-codec:commons-codec:1.15")
    implementation("com.auth0:java-jwt:3.19.1")

    //Valid
    implementation("org.springframework.boot:spring-boot-starter-validation")

    /* for activemq */
    implementation("org.springframework.boot:spring-boot-starter-activemq")
    implementation("org.apache.activemq:activemq-pool")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.querydsl:querydsl-apt:$querydslVersion")
    implementation("com.querydsl:querydsl-jpa:$querydslVersion")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

springBoot {
    buildInfo()
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}