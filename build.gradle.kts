plugins {
    kotlin("jvm") version "2.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    testImplementation(kotlin("test"))
//    testImplementation("io.kotest:kotest-runner-junit5:$version")
//    testImplementation("io.kotest:kotest-assertions-core:$version")
    implementation("io.kotest:kotest-runner-junit5:6.0.0.M16")
    implementation("io.kotest:kotest-assertions-core:6.0.0.M16")
//    testImplementation("io.kotest:kotest-runner-junit5:6.0.0.M16")
//    testImplementation("io.kotest:kotest-assertions-core:6.0.0.M16")

}

//tasks.test {
//    useJUnitPlatform()
//}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}