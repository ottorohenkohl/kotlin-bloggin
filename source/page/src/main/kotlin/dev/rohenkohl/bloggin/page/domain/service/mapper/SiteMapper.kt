package dev.rohenkohl.bloggin.page.domain.service.mapper

import dev.rohenkohl.bloggin.page.domain.model.Site
import dev.rohenkohl.bloggin.page.domain.model.repository.SiteRepository
import dev.rohenkohl.bloggin.page.domain.service.transfer.SiteTransfer
import dev.rohenkohl.bloggin.component.domain.service.LayoutService
import dev.rohenkohl.bloggin.component.domain.service.mapper.LayoutMapper
import dev.rohenkohl.bloggin.component.domain.service.mapper.WidgetMapper
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Loader
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class SiteMapper(val layoutMapper: LayoutMapper, val widgetMapper: WidgetMapper) : Exporter<SiteTransfer, Site>, Importer<SiteTransfer, Site>, Loader<Site>, Modifier<SiteTransfer, Site> {

    private lateinit var layoutService: LayoutService
    private lateinit var siteRepository: SiteRepository

    @Inject
    internal constructor(layoutMapper: LayoutMapper, layoutService: LayoutService, siteRepository: SiteRepository, widgetMapper: WidgetMapper) : this(layoutMapper, widgetMapper) {
        this.layoutService = layoutService
        this.siteRepository = siteRepository
    }


    override fun export(identifiable: Site): Content<SiteTransfer> {
        val siteTransfer = SiteTransfer(identifiable.layout.role, identifiable.name, identifiable.layout.visible, identifiable.key, widgetMapper.export(identifiable.layout.widget))

        return Content(siteTransfer, identifiable.uuid)
    }

    override fun import(transfer: SiteTransfer): Site {
        val layout = layoutService.store(transfer.widget.nonnull().payload, transfer.role.nonnull()) pipe layoutMapper::load

        layout.visible = transfer.visible ?: layout.visible

        return Site(transfer.name.nonnull(), transfer.key.nonnull(), layout) pipe siteRepository::create
    }

    override fun load(reference: Reference<Site>): Site {
        return siteRepository.readByUUID(reference.uuid)
    }

    override fun modify(content: Content<SiteTransfer>): Reference<Site> {
        val site = siteRepository.readByUUID(content.uuid)

        site.name = content.payload.name ?: site.name
        site.key = content.payload.key ?: site.key
        site.layout.visible = content.payload.visible ?: site.layout.visible

        return Reference(site)
    }
}