@file:Suppress("UnstableApiUsage")

import java.util.Locale.getDefault

plugins {
    id("platform")
    id("mod-resources")
    alias(libs.plugins.moddev)
}

val modId: String by project

modResources {
    filesMatching.add("META-INF/neoforge.mods.toml")
}

dependencies {
    implementation(libs.kotlin.neoforge)
}

neoForge {
    version = libs.versions.neoforge.asProvider().get()

    val at =
        project(":common").isolated.projectDirectory.file("src/main/resources/META-INF/accesstransformer.cfg").asFile
    if (at.exists() && at.isFile) accessTransformers.from(at.absolutePath)

    parchment {
        minecraftVersion = libs.versions.parchment.mc.get()
        mappingsVersion = libs.versions.parchment.asProvider().get()
    }

    runs {
        configureEach {
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
            ideName =
                "NeoForge ${name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(getDefault()) else it.toString() }} (${project.path})"
        }

        register("client") {
            client()
        }

        register("data") {
            data()
        }

        register("server") {
            server()
        }
    }

    mods {
        register(modId) {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets.main.get().resources { srcDir("src/generated/resources") }