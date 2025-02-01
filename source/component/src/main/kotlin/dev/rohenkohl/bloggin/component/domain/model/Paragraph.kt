package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.component.domain.model.constant.Size
import dev.rohenkohl.bloggin.component.domain.model.value.Text
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Paragraph(@Valid var size: Size, @Valid var text: Text) : Widget() {

    override fun <W> accept(visitor: Visitor<W>): W {
        return visitor.visit(this)
    }
}