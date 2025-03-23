package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.component.domain.model.constant.Alignment
import dev.rohenkohl.bloggin.component.domain.model.constant.Direction
import jakarta.persistence.*
import jakarta.persistence.CascadeType.REMOVE
import jakarta.validation.Valid

@Entity
class Listing(

    @field:Valid
    var across: Alignment,

    @field:Valid
    var direction: Direction,

    @field:Valid
    var main: Alignment,

    @field:OneToMany(cascade = [REMOVE])
    @field:OrderColumn
    val elements: MutableList<Widget> = mutableListOf()

) : Widget()