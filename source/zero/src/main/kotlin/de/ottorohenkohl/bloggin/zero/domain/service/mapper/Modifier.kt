package de.ottorohenkohl.bloggin.zero.domain.service.mapper

import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable

interface Modifier<D, I : Identifiable> {

    fun modify(content: Content<D>): Reference<I>
}