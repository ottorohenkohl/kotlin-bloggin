package dev.rohenkohl.bloggin.zero.domain.service.mapper

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable

interface Importer<D, I : Identifiable> {

    fun import(dto: D): I
}