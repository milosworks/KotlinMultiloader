@file:Suppress("UnstableApiUsage")

plugins {
    id("platform")
    id("mod-resources")
    alias(libs.plugins.loom)
}

val modId: String by project

modResources {
    filesMatching.add("fabric.mod.json")
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.layered {
        officialMojangMappings()
        parchment(
            "org.parchmentmc.data:parchment-${libs.versions.parchment.mc.get()}:${
                libs.versions.parchment.asProvider().get()
            }@zip"
        )
    })
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)

    modImplementation(libs.kotlin.fabric)
}

loom {
    val aw =
        project(":common").isolated.projectDirectory.file("src/main/resources/${modId}.accesswidener").asFile
    if (aw.exists() && aw.isFile) accessWidenerPath.set(aw)

    mixin {
        defaultRefmapName.set("$modId.refmap.json")
    }

    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
            runDir("runs/client")
        }

        named("server") {
            server()
            configName = "Fabric Server"
            ideConfigGenerated(true)
            runDir("runs/server")
        }
    }
}