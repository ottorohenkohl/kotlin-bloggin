package dev.rohenkohl.bloggin.zero.domain.exception.handler

import io.quarkus.logging.Log
import jakarta.transaction.RollbackException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class RollbackHandler : ExceptionMapper<RollbackException> {

    override fun toResponse(exception: RollbackException): Response {
        Log.debug("An error occurred: ", exception)

        val content = HashMap<String, Any>()

        content["error"] = "Bad Request"
        content["type"] = "Database transaction failed; Maybe duplicate or forbidden reference"

        return Response.status(Response.Status.BAD_REQUEST).entity(content).build()
    }
}