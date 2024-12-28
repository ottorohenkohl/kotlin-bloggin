package de.ottorohenkohl.bloggin.settings.domain.model

import de.ottorohenkohl.bloggin.settings.domain.model.constant.Level
import de.ottorohenkohl.bloggin.settings.domain.model.value.Text
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Header(@Valid var level: Level, @Valid var text: Text) : Widget() {

    override fun <W> accept(visitor: Visitor<W>): W {
        return visitor.visit(this)
    }
}