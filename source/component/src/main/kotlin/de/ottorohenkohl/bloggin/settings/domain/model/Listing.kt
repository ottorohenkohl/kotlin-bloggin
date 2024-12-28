package de.ottorohenkohl.bloggin.settings.domain.model

import de.ottorohenkohl.bloggin.settings.domain.model.constant.Alignment
import de.ottorohenkohl.bloggin.settings.domain.model.constant.Direction
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.OrderColumn
import jakarta.validation.Valid

@Entity
class Listing(@Valid var across: Alignment, @Valid var direction: Direction, @Valid var main: Alignment) : Widget() {

    @OneToMany
    @OrderColumn
    val elements = mutableListOf<Widget>()

    override fun <L> accept(visitor: Visitor<L>): L {
        return visitor.visit(this)
    }
}