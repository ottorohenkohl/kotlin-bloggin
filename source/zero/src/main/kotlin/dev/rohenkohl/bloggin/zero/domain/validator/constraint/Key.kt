package dev.rohenkohl.bloggin.zero.domain.validator.constraint

import dev.rohenkohl.bloggin.zero.domain.validator.KeyValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [KeyValidator::class])
annotation class Key(

    val message: String = "key contains invalid characters or is out of is size bounds",

    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)