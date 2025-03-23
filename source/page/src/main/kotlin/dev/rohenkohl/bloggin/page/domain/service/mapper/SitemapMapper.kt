package dev.rohenkohl.bloggin.page.domain.service.mapper

import dev.rohenkohl.bloggin.page.domain.model.Sitemap
import dev.rohenkohl.bloggin.page.domain.model.repository.SitemapRepository
import dev.rohenkohl.bloggin.page.domain.service.transfer.SitemapTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class SitemapMapper(val siteMapper: SiteMapper) : Exporter<SitemapTransfer, Sitemap>, Modifier<SitemapTransfer, Sitemap> {

    private lateinit var sitemapRepository: SitemapRepository

    @Inject
    internal constructor(siteMapper: SiteMapper, sitemapRepository: SitemapRepository) : this(siteMapper) {
        this.sitemapRepository = sitemapRepository
    }

    override fun export(identifiable: Sitemap): Content<SitemapTransfer> {
        val sitemapTransfer = SitemapTransfer(siteMapper.export(identifiable.landing))

        return Content(sitemapTransfer, identifiable.uuid)
    }

    override fun modify(content: Content<SitemapTransfer>): Reference<Sitemap> {
        val sitemap = sitemapRepository.readSingleton()

        sitemap.landing = if (content.payload.landing != null) siteMapper.load(Reference(content.payload.landing.nonnull().uuid)) else sitemap.landing

        return Reference(sitemap)
    }
}