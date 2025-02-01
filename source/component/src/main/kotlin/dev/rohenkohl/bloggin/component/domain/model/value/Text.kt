package dev.rohenkohl.bloggin.component.domain.model.value

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Embeddable
data class Text(@NotBlank @Size(min = 4, max = 10240) val value: String)