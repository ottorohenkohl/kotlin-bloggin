package dev.rohenkohl.bloggin.zero.domain.exception.handler

import dev.rohenkohl.bloggin.zero.domain.exception.NumberNegativeException
import io.quarkus.logging.Log
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class NumberNegativeHandler : ExceptionMapper<NumberNegativeException> {

    override fun toResponse(exception: NumberNegativeException): Response {
        Log.debug("An error occurred: ", exception)

        val content = HashMap<String, Any>()

        content["error"] = "Bad Request"
        content["type"] = "Number parameter should not be negative"

        return Response.status(Response.Status.BAD_REQUEST).entity(content).build()
    }
}