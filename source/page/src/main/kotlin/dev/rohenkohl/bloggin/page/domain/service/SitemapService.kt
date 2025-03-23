package dev.rohenkohl.bloggin.page.domain.service

import dev.rohenkohl.bloggin.page.domain.model.Sitemap
import dev.rohenkohl.bloggin.page.domain.model.repository.SiteRepository
import dev.rohenkohl.bloggin.page.domain.model.repository.SitemapRepository
import dev.rohenkohl.bloggin.page.domain.service.mapper.SitemapMapper
import dev.rohenkohl.bloggin.page.domain.service.transfer.SitemapTransfer
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.hibernate.query.Page

@ApplicationScoped
class SitemapService(val sitemapMapper: SitemapMapper) {

    private lateinit var sitemapRepository: SitemapRepository
    private lateinit var siteRepository: SiteRepository

    @Inject
    internal constructor(sitemapMapper: SitemapMapper, sitemapRepository: SitemapRepository, siteRepository: SiteRepository) : this(sitemapMapper) {
        this.sitemapRepository = sitemapRepository
        this.siteRepository = siteRepository
    }

    @Transactional
    fun change(content: Content<SitemapTransfer>): Reference<Sitemap> {
        if (sitemapRepository.notExistsSingleton()) store()

        return sitemapMapper.modify(content)
    }

    @Transactional
    fun find(): Content<SitemapTransfer> {
        if (sitemapRepository.notExistsSingleton()) store()

        return sitemapRepository.readSingleton() pipe sitemapMapper::export
    }

    @Transactional
    internal fun store(): Reference<Sitemap> {
        return siteRepository.readByPage(Page.first(1)).first() pipe { Sitemap(it) } pipe sitemapRepository::create pipe { Reference(it) }
    }
}