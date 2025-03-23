package dev.rohenkohl.bloggin.user.domain.exception.handler

import dev.rohenkohl.bloggin.user.domain.exception.LoginExistsException
import io.quarkus.logging.Log
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
internal class LoginExistsHandler : ExceptionMapper<LoginExistsException> {

    override fun toResponse(exception: LoginExistsException): Response {
        Log.debug("An error occurred: ", exception)

        val content = HashMap<String, Any>()

        content["error"] = "Login does already exist"
        content["type"] = "The current tenants login data is already associated with a login"

        return Response.status(Response.Status.CONFLICT).entity(content).build()
    }
}