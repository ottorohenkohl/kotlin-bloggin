package dev.rohenkohl.bloggin.page.domain.service

import dev.rohenkohl.bloggin.page.domain.model.Site
import dev.rohenkohl.bloggin.page.domain.model.repository.SiteRepository
import dev.rohenkohl.bloggin.page.domain.service.mapper.PreviewMapper
import dev.rohenkohl.bloggin.page.domain.service.mapper.SiteMapper
import dev.rohenkohl.bloggin.page.domain.service.transfer.PreviewTransfer
import dev.rohenkohl.bloggin.page.domain.service.transfer.SiteTransfer
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Range
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Range.Excerpt
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.pipe
import dev.rohenkohl.bloggin.zero.extension.yield
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ApplicationScoped
class SiteService (val previewMapper: PreviewMapper, val siteMapper: SiteMapper) {

    private lateinit var siteRepository: SiteRepository

    @Inject
    internal constructor(previewMapper: PreviewMapper, siteMapper: SiteMapper, siteRepository: SiteRepository) : this(previewMapper, siteMapper) {
        this.siteRepository = siteRepository
    }

    @Transactional
    fun change(content: Content<SiteTransfer>): Reference<Site> {
        return siteMapper.modify(content)
    }

    @Transactional
    fun erase(reference: Reference<Site>): Reference<Site> {
        return siteMapper.load(reference) pipe siteRepository::delete yield reference
    }

    @Transactional
    fun find(range: Range<PreviewTransfer>): Excerpt<PreviewTransfer> {
        return siteRepository.readByPage(range.page()).map(previewMapper::export) pipe range::fill
    }

    @Transactional
    fun find(reference: Reference<Site>): Content<SiteTransfer> {
        return siteMapper.load(reference) pipe siteMapper::export
    }

    @Transactional
    fun store(siteTransfer: SiteTransfer): Reference<Site> {
        return siteMapper.import(siteTransfer) pipe { Reference(it) }
    }
}