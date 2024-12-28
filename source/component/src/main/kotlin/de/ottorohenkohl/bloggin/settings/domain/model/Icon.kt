package de.ottorohenkohl.bloggin.settings.domain.model

import de.ottorohenkohl.bloggin.settings.domain.model.constant.Primeicon
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Icon(@Valid var primeicon: Primeicon) : Widget() {

    override fun <W> accept(visitor: Visitor<W>): W {
        return visitor.visit(this)
    }
}