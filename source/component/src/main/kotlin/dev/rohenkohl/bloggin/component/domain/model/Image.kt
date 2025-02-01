package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.component.domain.model.value.Data
import dev.rohenkohl.bloggin.component.domain.model.value.Mimetype
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Image(@Valid var data: Data, @Valid var mimetype: Mimetype) : Widget() {

    override fun <W> accept(visitor: Visitor<W>): W {
        return visitor.visit(this)
    }
}