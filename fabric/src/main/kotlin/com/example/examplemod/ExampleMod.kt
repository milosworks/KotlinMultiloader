package com.example.examplemod

import net.fabricmc.api.ModInitializer

object ExampleMod : ModInitializer {
    override fun onInitialize() {
        Constants.LOG.info("Hello Fabric world from Kotlin!")
        CommonObject.init()
    }
}