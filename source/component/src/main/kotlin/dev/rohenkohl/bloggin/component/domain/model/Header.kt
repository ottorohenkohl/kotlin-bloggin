package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.component.domain.model.constant.Level
import dev.rohenkohl.bloggin.zero.domain.validator.constraint.Snippet
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Header(

    @field:Valid
    var level: Level,

    @field:Snippet
    var text: String

) : Widget()