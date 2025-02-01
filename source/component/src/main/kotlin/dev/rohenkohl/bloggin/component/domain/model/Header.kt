package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.component.domain.model.constant.Level
import dev.rohenkohl.bloggin.component.domain.model.value.Text
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Header(@Valid var level: Level, @Valid var text: Text) : Widget() {

    override fun <W> accept(visitor: Visitor<W>): W {
        return visitor.visit(this)
    }
}