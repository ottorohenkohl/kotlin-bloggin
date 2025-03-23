package dev.rohenkohl.bloggin.zero.domain.service.mapper

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable

interface Importer<T, I : Identifiable> {

    fun import(transfer: T): I
}