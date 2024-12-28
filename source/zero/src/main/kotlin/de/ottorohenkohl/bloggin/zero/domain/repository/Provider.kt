package de.ottorohenkohl.bloggin.zero.domain.repository

import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.EntityManager
import java.util.UUID


interface Provider<I : Identifiable> {

    val entityManager: EntityManager

    fun read(): I

    fun readByUUID(uuid: UUID): I
}