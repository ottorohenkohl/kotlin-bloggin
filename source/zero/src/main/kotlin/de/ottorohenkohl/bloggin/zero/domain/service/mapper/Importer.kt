package de.ottorohenkohl.bloggin.zero.domain.service.mapper

import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable

interface Importer<D, I : Identifiable> {

    fun import(dto: D): I
}