package de.ottorohenkohl.bloggin.zero.domain.repository

import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable
import de.ottorohenkohl.bloggin.zero.extension.yield
import jakarta.persistence.EntityManager
import java.util.UUID

interface Repository<I : Identifiable> {

    val entityManager: EntityManager

    fun readByUUID(uuid: UUID): I

    fun create(individual: I): I {
        return entityManager.persist(individual) yield individual
    }

    fun delete(individual: I): I {
        return entityManager.remove(individual) yield individual
    }
}
