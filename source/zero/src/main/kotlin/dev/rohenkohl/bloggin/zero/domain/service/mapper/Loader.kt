package dev.rohenkohl.bloggin.zero.domain.service.mapper

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference

interface Loader<I : Identifiable> {

    fun load(reference: Reference<I>): I
}