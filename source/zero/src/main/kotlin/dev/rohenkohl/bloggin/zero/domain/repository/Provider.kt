package dev.rohenkohl.bloggin.zero.domain.repository

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.EntityManager
import java.util.*


interface Provider<I : Identifiable> {

    val entityManager: EntityManager

    fun read(): I

    fun readByUUID(uuid: UUID): I
}