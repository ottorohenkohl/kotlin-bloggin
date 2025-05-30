package dev.rohenkohl.bloggin.zero.domain.service.mapper

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content

interface Exporter<T, I : Identifiable> {

    fun export(identifiable: I): Content<T>
}