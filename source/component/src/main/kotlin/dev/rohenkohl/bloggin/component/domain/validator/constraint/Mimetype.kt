package dev.rohenkohl.bloggin.component.domain.validator.constraint

import dev.rohenkohl.bloggin.component.domain.validator.MimetypeValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [MimetypeValidator::class])
annotation class Mimetype(

    val message: String = "mimetype not supported",

    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Payload>> = []

)