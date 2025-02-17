package dev.rohenkohl.bloggin.configuration.domain.service

import jakarta.enterprise.context.RequestScoped
import jakarta.ws.rs.InternalServerErrorException
import org.eclipse.microprofile.config.ConfigProvider

@RequestScoped
class EnvironmentService {

    fun boolean(key: String, default: Boolean? = null) = value(key, default, Boolean::class.java)
    fun int(key: String, default: Int? = null) = value(key, default, Int::class.java)
    fun string(key: String, default: String? = null) = value(key, default, String::class.java)

    private fun <T> value(key: String, default: T?, type: Class<T>): T {
        return ConfigProvider.getConfig().getOptionalValue(key, type).orElse(default) ?: throw InternalServerErrorException()
    }
}