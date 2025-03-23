package dev.rohenkohl.bloggin.user.domain.exception.handler

import dev.rohenkohl.bloggin.user.domain.exception.PersonExistsException
import io.quarkus.logging.Log
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
internal class PersonExistsHandler : ExceptionMapper<PersonExistsException> {

    override fun toResponse(exception: PersonExistsException): Response {
        Log.debug("An error occurred: ", exception)

        val content = HashMap<String, Any>()

        content["error"] = "Person does already exist"
        content["type"] = "The person data does already exist"

        return Response.status(Response.Status.CONFLICT).entity(content).build()
    }
}