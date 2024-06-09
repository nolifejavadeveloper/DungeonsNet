plugins {
    id("java")
}

group = "net.dungeons"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation('com.github.Minestom:Minestom:-b3aa996e1d-1')
}

tasks.test {
    useJUnitPlatform()
}