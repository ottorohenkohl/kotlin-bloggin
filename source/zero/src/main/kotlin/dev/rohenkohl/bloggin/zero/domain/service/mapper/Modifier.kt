package dev.rohenkohl.bloggin.zero.domain.service.mapper

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content

interface Modifier<D, I : Identifiable> {

    fun modify(content: Content<D>): Reference<I>
}