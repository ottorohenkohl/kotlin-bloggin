package dev.rohenkohl.bloggin.component.domain.model.value

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.Pattern

@Embeddable
data class Mimetype(@Pattern(regexp = "(image/jpeg|image/png)") val value: String)