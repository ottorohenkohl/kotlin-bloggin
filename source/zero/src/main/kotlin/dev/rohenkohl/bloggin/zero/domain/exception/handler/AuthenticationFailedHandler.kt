package dev.rohenkohl.bloggin.zero.domain.exception.handler

import io.quarkus.logging.Log
import io.quarkus.security.AuthenticationFailedException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class AuthenticationFailedHandler : ExceptionMapper<AuthenticationFailedException> {

    override fun toResponse(exception: AuthenticationFailedException): Response {
        Log.debug("An error occurred: ", exception)

        val content = HashMap<String, Any>()

        content["error"] = "Not Authorized"
        content["type"] = "The current user could not be authenticated or is not authorized for this action"

        return Response.status(Response.Status.FORBIDDEN).entity(content).build()
    }
}