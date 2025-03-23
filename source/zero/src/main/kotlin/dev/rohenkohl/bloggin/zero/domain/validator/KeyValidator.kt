package dev.rohenkohl.bloggin.zero.domain.validator

import dev.rohenkohl.bloggin.zero.domain.validator.constraint.Key
import jakarta.enterprise.context.ApplicationScoped
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

@ApplicationScoped
internal class KeyValidator : ConstraintValidator<Key, String> {

    override fun isValid(text: String, context: ConstraintValidatorContext): Boolean {
        return Regex("^[0-9a-zA-Z-_:/]{1,128}$").matches(text)
    }
}