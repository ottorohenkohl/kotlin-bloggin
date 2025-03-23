package dev.rohenkohl.bloggin.zero.domain.validator.constraint

import dev.rohenkohl.bloggin.zero.domain.validator.TextValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [TextValidator::class])
annotation class Text(

    val message: String = "text is not within it's size bounds",

    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []

)