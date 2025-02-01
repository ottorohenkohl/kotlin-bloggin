package dev.rohenkohl.bloggin.zero.handler

import io.quarkus.logging.Log
import jakarta.persistence.NoResultException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class NoResultHandler : ExceptionMapper<NoResultException> {

    override fun toResponse(exception: NoResultException): Response {
        Log.debug("An error occurred: ", exception)

        val content = HashMap<String, Any>()

        content["error"] = "Not Found"
        content["type"] = "Requested Resource is not available or does not exist"

        return Response.status(Response.Status.NOT_FOUND).entity(content).build()
    }
}