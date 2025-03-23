package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Layout
import dev.rohenkohl.bloggin.component.domain.model.repository.LayoutRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.LayoutTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Loader
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class LayoutMapper(): Exporter<LayoutTransfer, Layout>, Loader<Layout> {

    private lateinit var layoutRepository: LayoutRepository

    @Inject
    internal constructor(layoutRepository: LayoutRepository) : this() {
        this.layoutRepository = layoutRepository
    }

    override fun export(identifiable: Layout): Content<LayoutTransfer> {
        val widgetReference = Reference(identifiable.widget)
        val layoutTransfer = LayoutTransfer(identifiable.role, identifiable.visible, widgetReference)

        return Content(layoutTransfer, identifiable.uuid)
    }

    override fun load(reference: Reference<Layout>): Layout {
        return layoutRepository.readByUUID(reference.uuid)
    }
}