package de.ottorohenkohl.bloggin.zero.domain.model.value

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import jakarta.ws.rs.BadRequestException

@Embeddable
data class Name(@NotBlank @Size(min = 2, max = 16) val value: String)