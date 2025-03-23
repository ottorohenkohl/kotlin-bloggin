package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.component.domain.model.constant.Fontsize
import dev.rohenkohl.bloggin.zero.domain.validator.constraint.Text
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Paragraph(

    @field:Valid
    var fontsize: Fontsize,

    @field:Text
    var text: String

) : Widget()