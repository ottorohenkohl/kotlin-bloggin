package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.component.domain.model.constant.Direction
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Separator(

    @field:Valid
    var direction: Direction

) : Widget()