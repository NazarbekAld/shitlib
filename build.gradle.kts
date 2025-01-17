buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.github.johnrengelman:shadow:8.1.1")
    }
}

plugins {
    id("java")
}

group = "us.and.everyone.else.who.wants.to.contribute"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

fun useSpigotRepo() {
    repositories {
        maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

fun ohAndAlsoUseSpigotAsDependency(version: String) {
    dependencies {
        implementation("org.spigotmc:spigot-api:${version}-R0.1-SNAPSHOT")
    }
}

useSpigotRepo()


addShadowJarPluginToTheProjectToShadeSpigotIntoIt()


ohAndAlsoUseSpigotAsDependency("1.8.8")

val mctestdir = File("C:\\mctest\\plugins")
if(mctestdir.isDirectory) {
    tasks {
        "jar"(Jar::class) {
            archiveFileName.set("shitlib.jar")
            destinationDirectory.set(mctestdir)
        }
    }
} else {
    println("C:\\mctest\\plugins is not a directory, not copying jar")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(Integer.valueOf("8")))
    }
}


fun addShadowJarPluginToTheProjectToShadeSpigotIntoIt() {
    apply(plugin = "com.github.johnrengelman.shadow")
}