package dev.rohenkohl.bloggin.zero.domain.model.repository

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import dev.rohenkohl.bloggin.zero.extension.yield
import jakarta.persistence.EntityManager
import java.util.*

interface Repository<I : Identifiable> {

    val entityManager: EntityManager

    fun create(individual: I) = entityManager.persist(individual) yield individual

    fun delete(individual: I) = entityManager.remove(individual) yield individual
}