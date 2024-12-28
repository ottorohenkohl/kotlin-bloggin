package de.ottorohenkohl.bloggin.settings.domain.model

import de.ottorohenkohl.bloggin.settings.domain.model.value.Mimetype
import de.ottorohenkohl.bloggin.settings.domain.model.value.Data
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Image(@Valid var data: Data, @Valid var mimetype: Mimetype) : Widget() {

    override fun <W> accept(visitor: Visitor<W>): W {
        return visitor.visit(this)
    }
}