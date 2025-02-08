plugins {
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.google.protobuf:protobuf-java:3.23.4")
    implementation("io.grpc:grpc-stub:1.57.0")
    implementation("io.grpc:grpc-protobuf:1.57.0")

    implementation("com.example:grpc-contract:1.0.0")

    implementation("org.jdbi:jdbi3-core:3.28.0")

    implementation("org.jdbi:jdbi3-postgres:3.41.0")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("com.zaxxer:HikariCP:5.0.1")

    implementation("ch.qos.logback:logback-classic:1.4.12")

    implementation("io.grpc:grpc-netty-shaded:1.68.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}