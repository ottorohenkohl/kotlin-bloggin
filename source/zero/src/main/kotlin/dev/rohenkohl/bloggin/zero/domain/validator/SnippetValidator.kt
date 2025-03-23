package dev.rohenkohl.bloggin.zero.domain.validator

import dev.rohenkohl.bloggin.zero.domain.validator.constraint.Snippet
import jakarta.enterprise.context.ApplicationScoped
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

@ApplicationScoped
internal class SnippetValidator : ConstraintValidator<Snippet, String> {

    override fun isValid(text: String, context: ConstraintValidatorContext): Boolean {
        return text.length in 2..128
    }
}