package dev.rohenkohl.bloggin.zero.extension

import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import io.quarkus.logging.Log
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.core.UriInfo
import java.net.URI
import kotlin.reflect.KClass

inline fun <reified T : Throwable> fail(function: () -> Any, throwable: Throwable) = try {
    function() throws throwable
} catch (any: Throwable) {
    if (any is T) null else throw throwable
}

inline fun <reified T : Throwable, R> rethrow(function: () -> R, throwable: Throwable) = try {
    function()
} catch (any: Throwable) {
    if (any is T) throw throwable else function()
}

fun <T> T?.nonnull(): T = this ?: throw BadRequestException()

fun UriInfo.path(reference: Reference<*>): URI = absolutePathBuilder.path(reference.uuid.toString()).build()
fun UriInfo.path(reference: Reference<*>, resource: KClass<*>): URI = absolutePathBuilder.path(resource.java).path(reference.uuid.toString()).build()

infix fun <T, R> T.pipe(block: (T) -> R): R = block(this)
infix fun <T> T.throws(throwable: Throwable): Unit = throw throwable
infix fun <T, R> T.yield(other: R) = other
