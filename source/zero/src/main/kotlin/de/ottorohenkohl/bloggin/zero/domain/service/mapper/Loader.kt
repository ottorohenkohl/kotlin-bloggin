package de.ottorohenkohl.bloggin.zero.domain.service.mapper

import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable

interface Loader<I : Identifiable> {

    fun load(reference: Reference<I>): I
}