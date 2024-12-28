package de.ottorohenkohl.bloggin.zero.domain.model.value

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import jakarta.ws.rs.BadRequestException

@Embeddable
data class Description(@NotBlank @Size(min = 2, max = 64) val value: String)