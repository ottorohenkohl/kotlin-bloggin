package de.ottorohenkohl.bloggin.zero.extension

import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.core.UriInfo
import java.net.URI
import java.util.UUID
import kotlin.reflect.KClass

fun UriInfo.path(reference: Reference<*>): URI = absolutePathBuilder.path(reference.uuid.toString()).build()
fun UriInfo.path(reference: Reference<*>, resource: KClass<*>): URI = absolutePathBuilder.path(resource.java).path(reference.uuid.toString()).build()

fun <T> T?.nonnull(): T = this ?: throw BadRequestException()

infix fun <T, R> T.pipe(block: (T) -> R): R = block(this)
infix fun <T, R> T.yield(other: R) = other
