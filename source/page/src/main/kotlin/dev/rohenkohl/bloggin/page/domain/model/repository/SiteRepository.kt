package dev.rohenkohl.bloggin.page.domain.model.repository

import dev.rohenkohl.bloggin.page.domain.model.Site
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import org.hibernate.query.Page
import java.util.*

internal interface SiteRepository : Repository<Site> {

    @Find
    fun readByUUID(uuid: UUID): Site

    @Find
    fun readByPage(page: Page): List<Site>
}
