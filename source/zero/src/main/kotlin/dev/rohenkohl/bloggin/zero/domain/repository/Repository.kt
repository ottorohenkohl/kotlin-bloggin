package dev.rohenkohl.bloggin.zero.domain.repository

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import dev.rohenkohl.bloggin.zero.extension.yield
import jakarta.persistence.EntityManager
import java.util.*

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
