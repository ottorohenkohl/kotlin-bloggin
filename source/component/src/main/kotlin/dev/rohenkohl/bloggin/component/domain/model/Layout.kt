package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.*
import jakarta.persistence.CascadeType.REMOVE
import jakarta.validation.Valid
import org.jetbrains.annotations.NotNull

@Entity
class Layout(

    @field:Valid
    var role: Role,

    @field:NotNull
    var visible: Boolean,

    @field:ManyToOne(cascade = [REMOVE])
    var widget: Widget,

    @field:OneToMany(cascade = [REMOVE])
    var owning: MutableList<Widget> = mutableListOf(),

) : Identifiable()