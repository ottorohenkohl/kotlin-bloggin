package dev.rohenkohl.bloggin.zero.domain.exception.handler

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException
import io.quarkus.logging.Log
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class InvalidDefinitionHandler : ExceptionMapper<InvalidDefinitionException> {

    override fun toResponse(exception: InvalidDefinitionException): Response {
        Log.debug("An error occurred: ", exception)

        val content = HashMap<String, Any>()

        content["error"] = "Bad Request"
        content["type"] = "Request body could not be deserialized because of missing type information"

        return Response.status(Response.Status.BAD_REQUEST).entity(content).build()
    }
}