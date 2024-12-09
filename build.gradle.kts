plugins {
  id("java")
}

repositories {
  maven {
    name = "papermc"
    url = uri("https://repo.papermc.io/repository/maven-public/")
  }
}

dependencies {
  compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
}

tasks {
  processResources {
    filesMatching("paper-plugin.yml") {
      expand("version" to project.version)
    }
  }

  withType<JavaCompile> {
    options.encoding = "UTF-8"
  }

  java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
  }

  jar {
    archiveFileName.set("${project.name}-${rootProject.version}.jar")
  }
}