package dev.rohenkohl.bloggin.zero.domain.exception.handler

import io.quarkus.logging.Log
import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class ConstraintViolationHandler : ExceptionMapper<ConstraintViolationException> {

    override fun toResponse(exception: ConstraintViolationException): Response {
        Log.debug("An error occurred: ", exception)

        val violations = exception.constraintViolations.stream().map(::toViolation).toList()
        val content = HashMap<String, Any>()

        content["error"] = "Bad Request"
        content["type"] = "Constraint Violation"
        content["violations"] = violations

        return Response.status(Response.Status.BAD_REQUEST).entity(content).build()
    }

    private fun toViolation(constraintViolation: ConstraintViolation<*>): Map<String, String> {
        val violation = HashMap<String, String>()

        violation["location"] = constraintViolation.leafBean.javaClass.simpleName
        violation["message"] = constraintViolation.message
        violation["target"] = constraintViolation.propertyPath.toString()

        return violation
    }
}