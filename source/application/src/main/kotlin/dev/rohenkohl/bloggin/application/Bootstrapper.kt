package dev.rohenkohl.bloggin.application

import io.quarkus.logging.Log
import io.quarkus.runtime.Startup
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
internal class Bootstrapper {

    @Startup
    fun bootstrap() {
        Log.info("Starting bloggin application")
    }
}