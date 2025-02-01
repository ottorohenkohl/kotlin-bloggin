package dev.rohenkohl.bloggin.configuration.domain.repository

import dev.rohenkohl.bloggin.configuration.domain.model.Configuration
import dev.rohenkohl.bloggin.zero.domain.repository.Provider
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface ConfigurationRepository : Provider<Configuration> {

    override fun read(): Configuration {
        val query = entityManager.criteriaBuilder.createQuery(Configuration::class.java)
        val root = query.from(Configuration::class.java)

        query.select(root)

        return entityManager.createQuery(query).resultList.single()
    }

    @Find
    override fun readByUUID(uuid: UUID): Configuration
}