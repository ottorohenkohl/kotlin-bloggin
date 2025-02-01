package dev.rohenkohl.bloggin.person.extension

import dev.rohenkohl.bloggin.person.domain.model.value.Issuer
import dev.rohenkohl.bloggin.person.domain.model.value.Subject
import jakarta.ws.rs.BadRequestException


fun String?.nonnullIssuer(): Issuer =
    if (this != null) Issuer(this) else throw BadRequestException("Issuer cannot be null")

fun String?.nullableIssuer(): Issuer? = if (this != null) Issuer(this) else null

fun String?.nonnullSubject(): Subject =
    if (this != null) Subject(this) else throw BadRequestException("Subject cannot be null")

fun String?.nullableSubject(): Subject? = if (this != null) Subject(this) else null
