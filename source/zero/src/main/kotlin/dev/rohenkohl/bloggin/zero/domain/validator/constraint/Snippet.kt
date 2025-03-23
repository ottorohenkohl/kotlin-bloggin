package dev.rohenkohl.bloggin.zero.domain.validator.constraint

import dev.rohenkohl.bloggin.zero.domain.validator.SnippetValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [SnippetValidator::class])
annotation class Snippet(

    val message: String = "snippet is not within it's size bounds",

    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []

)