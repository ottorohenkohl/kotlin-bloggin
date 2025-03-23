package dev.rohenkohl.bloggin.component.domain.validator

import dev.rohenkohl.bloggin.component.domain.validator.constraint.Mimetype
import jakarta.enterprise.context.ApplicationScoped
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

@ApplicationScoped
internal class MimetypeValidator : ConstraintValidator<Mimetype, String> {

    override fun isValid(text: String, context: ConstraintValidatorContext): Boolean {
        return Regex("^(image/jpeg|image/png)$").matches(text)
    }
}