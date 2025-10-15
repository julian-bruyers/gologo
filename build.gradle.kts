import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML

plugins {
    id("java")
    alias(libs.plugins.intelliJPlatform)
    alias(libs.plugins.changelog)
}

group = "com.julianbruyers.gologo"
version = "1.2.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}


repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}


dependencies {
    intellijPlatform {
        goland(providers.gradleProperty("platformVersion"))
        bundledPlugins("org.jetbrains.plugins.go")
        instrumentationTools()
        pluginVerifier()
        zipSigner()
    }

    testImplementation(libs.junit)
}

intellijPlatform {
    pluginConfiguration {
        name = providers.gradleProperty("pluginName")
        version.set(project.version as String)

        description = providers.fileContents(layout.projectDirectory.file("PLUGIN.md")).asText.map {
            val start = "<!-- Plugin description -->"
            val end = "<!-- Plugin description end -->"

            with(it.lines()) {
                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in PLUGIN.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end)).joinToString("\n").let(::markdownToHTML)
            }
        }

        val changelog = project.changelog
        changeNotes = providers.gradleProperty("pluginVersion").map {
            with(changelog) {
                renderItem(
                    (getOrNull(it) ?: getUnreleased())
                        .withHeader(false)
                        .withEmptySections(false),
                    Changelog.OutputType.HTML,
                )
            }
        }

        ideaVersion {
            sinceBuild = providers.gradleProperty("pluginSinceBuild")
            untilBuild = providers.gradleProperty("pluginUntilBuild")
        }
    }

    pluginVerification {
        ides {
            recommended()
        }
    }
}


changelog {
    groups.empty()
    repositoryUrl = providers.gradleProperty("pluginRepositoryUrl")
}


tasks {
    // Skip buildSearchableOptions task to avoid headless mode issues
    buildSearchableOptions {
        enabled = false
    }

    // Skip prepareJarSearchableOptions task to avoid directory issues
    prepareJarSearchableOptions {
        enabled = false
    }
}
