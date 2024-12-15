plugins {
  id("java")
  id("io.github.goooler.shadow") version "8.1.8"
}

repositories {
  maven {
    name = "papermc"
    url = uri("https://repo.papermc.io/repository/maven-public/")
  }
  maven {
    name = "triumph"
    url = uri("https://repo.triumphteam.dev/snapshots/")
  }
}

dependencies {
  compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
  implementation("dev.triumphteam:triumph-cmd-bukkit:2.0.0-SNAPSHOT")
}

tasks {
  processResources {
    filesMatching("paper-plugin.yml") {
      expand("version" to project.version)
    }
  }

  shadowJar {
    relocate("dev.triumphteam.cmd", "top.jonakls.pariunt.libs.cmd")

    archiveFileName.set("${project.name}-${rootProject.version}.jar")
  }

  withType<JavaCompile> {
    options.encoding = "UTF-8"
  }

  java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
  }
}