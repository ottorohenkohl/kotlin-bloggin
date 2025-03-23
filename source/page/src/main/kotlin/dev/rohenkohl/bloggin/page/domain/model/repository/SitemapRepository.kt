package dev.rohenkohl.bloggin.page.domain.model.repository

import dev.rohenkohl.bloggin.page.domain.model.Sitemap
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface SitemapRepository : Repository<Sitemap> {

    fun notExistsSingleton(): Boolean {
        val builder = entityManager.criteriaBuilder
        val query = builder.createQuery(Sitemap::class.java)
        val root = query.from(Sitemap::class.java)

        query.select(root)

        return entityManager.createQuery(query).resultList.isEmpty()
    }

    @Find
    fun readByUUID(uuid: UUID): Sitemap

    fun readSingleton(): Sitemap {
        val builder = entityManager.criteriaBuilder
        val query = builder.createQuery(Sitemap::class.java)
        val root = query.from(Sitemap::class.java)

        query.select(root)

        return entityManager.createQuery(query).resultList.removeFirst()
    }
}