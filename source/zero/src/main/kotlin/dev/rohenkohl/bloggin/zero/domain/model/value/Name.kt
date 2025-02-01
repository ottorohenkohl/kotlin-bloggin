package dev.rohenkohl.bloggin.zero.domain.model.value

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Embeddable
data class Name(@NotBlank @Size(min = 2, max = 16) val value: String)