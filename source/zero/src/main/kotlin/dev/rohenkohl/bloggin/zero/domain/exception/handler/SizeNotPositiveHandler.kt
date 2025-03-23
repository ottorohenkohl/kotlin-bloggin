package dev.rohenkohl.bloggin.zero.domain.exception.handler

import dev.rohenkohl.bloggin.zero.domain.exception.SizeNotPositiveException
import io.quarkus.logging.Log
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class SizeNotPositiveHandler : ExceptionMapper<SizeNotPositiveException> {

    override fun toResponse(exception: SizeNotPositiveException): Response {
        Log.debug("An error occurred: ", exception)

        val content = HashMap<String, Any>()

        content["error"] = "Bad Request"
        content["type"] = "Size parameter has to be strictly positive"

        return Response.status(Response.Status.BAD_REQUEST).entity(content).build()
    }
}