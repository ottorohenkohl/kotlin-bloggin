package de.ottorohenkohl.bloggin.person.domain.model.value

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotBlank

@Embeddable
data class Subject(@NotBlank val value: String)