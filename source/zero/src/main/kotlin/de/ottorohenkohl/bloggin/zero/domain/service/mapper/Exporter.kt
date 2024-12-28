package de.ottorohenkohl.bloggin.zero.domain.service.mapper

import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable

interface Exporter<D, I : Identifiable> {

    fun export(identifiable: I): Content<D>
}