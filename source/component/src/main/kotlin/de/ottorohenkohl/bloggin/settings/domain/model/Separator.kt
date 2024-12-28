package de.ottorohenkohl.bloggin.settings.domain.model

import de.ottorohenkohl.bloggin.settings.domain.model.constant.Direction
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Separator(@Valid var direction: Direction) : Widget() {

    override fun <W> accept(visitor: Visitor<W>): W {
        return visitor.visit(this)
    }
}