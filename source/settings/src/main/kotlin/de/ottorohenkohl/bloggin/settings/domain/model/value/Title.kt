package de.ottorohenkohl.bloggin.settings.domain.model.value

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Embeddable
data class Title(@NotBlank @Size(min = 4, max = 32) val value: String)