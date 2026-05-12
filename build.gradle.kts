plugins {
    `java-library`
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}


group = "sh.twenty.octane"
version = "1.0-SNAPSHOT"


val paperVersion = "1.21.1-R0.1-SNAPSHOT"


repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}



dependencies {
    compileOnly("io.papermc.paper:paper-api:$paperVersion")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}



tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Xlint:unchecked", "-Werror"))
}


bukkit {
    main = "sh.twenty.octane.Main"
    name = "octane"
    version = project.version.toString()
    apiVersion = "1.21"
    author = "twenty1989"
    description = "something made by real people"

    commands {
        register("heal") {
            description = "Restores a player's health to full."
            usage = "/heal"
        }
        register("feed") {
            description = "Restores a player's hunger to full."
            usage = "/feed"
        }
        register("octane"){
            description = "Reloads the plugins configuration files."
            usage = "/octane reload"
        }
    }
}

