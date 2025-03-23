package dev.rohenkohl.bloggin.zero.domain.exception.handler

import io.quarkus.logging.Log
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class NullPointerHandler : ExceptionMapper<NullPointerException> {

    override fun toResponse(exception: NullPointerException): Response {
        Log.debug("An error occurred: ", exception)

        val content = HashMap<String, Any>()

        content["error"] = "Bad Request"
        content["type"] = "Required parameter should not be null"

        return Response.status(Response.Status.BAD_REQUEST).entity(content).build()
    }
}