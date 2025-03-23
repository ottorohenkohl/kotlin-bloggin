package dev.rohenkohl.bloggin.zero.extension

import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import jakarta.ws.rs.core.UriInfo
import java.net.URI
import kotlin.reflect.KClass

fun <T> T?.nonnull(): T = this ?: throw NullPointerException()

fun UriInfo.path(reference: Reference<*>): URI = absolutePathBuilder.path(reference.uuid.toString()).build()
fun UriInfo.path(reference: Reference<*>, resource: KClass<*>): URI = baseUriBuilder.path(resource.java).path(reference.uuid.toString()).build()

infix fun <T, R> T.pipe(block: (T) -> R): R = block(this)
infix fun <T, R> T.yield(other: R) = other