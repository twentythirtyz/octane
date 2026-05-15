plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    id("com.gradleup.shadow") version "9.4.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "sh.twenty.octane"
version = "1.2-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

val commandApiVersion = "9.5.3"

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")

    implementation("dev.jorel:commandapi-bukkit-shade:$commandApiVersion")
    implementation("dev.jorel:commandapi-bukkit-kotlin:$commandApiVersion") { isTransitive = false }
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.3")
    implementation("com.charleskorn.kaml:kaml:0.61.0")

    testImplementation("com.github.seeseemelk:MockBukkit-v1.21:3.133.2")
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        relocate("dev.jorel.commandapi", "sh.twenty.octane.lib.commandapi")
        relocate("com.charleskorn.kaml", "sh.twenty.octane.lib.kaml")
    }
    build { dependsOn(shadowJar) }
    test { useJUnitPlatform() }
}

bukkit {
    main = "sh.twenty.octane.OctanePlugin"
    name = "octane"
    version = project.version.toString()
    apiVersion = "1.21"
    author = "twentythirtyz"
    description = "lightweight solution to heavy problems"
}
