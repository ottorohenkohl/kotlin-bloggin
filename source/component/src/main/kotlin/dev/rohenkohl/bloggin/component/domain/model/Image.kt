package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.component.domain.validator.constraint.Mimetype
import jakarta.persistence.Entity
import jakarta.persistence.Lob
import jakarta.validation.constraints.NotEmpty

@Entity
class Image(

    @field:Mimetype
    var mimetype: String,

    @field:Lob
    @field:NotEmpty
    val data: List<Byte>

) : Widget()