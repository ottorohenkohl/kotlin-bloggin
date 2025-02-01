package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.Entity

@Entity
abstract class Widget : Identifiable() {

    internal abstract fun <W> accept(visitor: Visitor<W>): W

    internal interface Visitor<W> {

        fun visit(header: Header): W

        fun visit(icon: Icon): W

        fun visit(image: Image): W

        fun visit(listing: Listing): W

        fun visit(paragraph: Paragraph): W

        fun visit(separator: Separator): W
    }
}