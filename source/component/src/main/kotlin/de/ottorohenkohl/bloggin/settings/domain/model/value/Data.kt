package de.ottorohenkohl.bloggin.settings.domain.model.value

import jakarta.persistence.Embeddable
import jakarta.persistence.Lob
import jakarta.validation.constraints.NotEmpty

@Embeddable
data class Data(@NotEmpty @Lob val value: List<Byte>)