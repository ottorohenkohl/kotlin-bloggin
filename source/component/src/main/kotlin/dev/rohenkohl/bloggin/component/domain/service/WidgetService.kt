package dev.rohenkohl.bloggin.component.domain.service

import dev.rohenkohl.bloggin.component.domain.model.Widget
import dev.rohenkohl.bloggin.component.domain.model.repository.LayoutRepository
import dev.rohenkohl.bloggin.component.domain.model.repository.ListingRepository
import dev.rohenkohl.bloggin.component.domain.model.repository.WidgetRepository
import dev.rohenkohl.bloggin.component.domain.service.mapper.WidgetMapper
import dev.rohenkohl.bloggin.component.domain.service.transfer.WidgetTransfer
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.pipe
import dev.rohenkohl.bloggin.zero.extension.yield
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@RequestScoped
class WidgetService(val widgetMapper: WidgetMapper) {

    private lateinit var layoutRepository: LayoutRepository
    private lateinit var listingRepository: ListingRepository
    private lateinit var widgetRepository: WidgetRepository

    @Inject
    internal constructor(layoutRepository: LayoutRepository, listingRepository: ListingRepository, widgetMapper: WidgetMapper, widgetRepository: WidgetRepository) : this(widgetMapper) {
        this.layoutRepository = layoutRepository
        this.listingRepository = listingRepository
        this.widgetRepository = widgetRepository
    }

    @Transactional
    fun change(content: Content<WidgetTransfer>): Reference<Widget> {
        return widgetMapper.modify(content)
    }

    @Transactional
    fun erase(reference: Reference<Widget>): Reference<Widget> {
        val widget = widgetRepository.readByUUID(reference.uuid)

        layoutRepository.readByWidget(widget).owning.remove(widget)
        listingRepository.readByWidget(widget).elements.remove(widget)

        return widgetRepository.delete(widget) yield reference
    }

    @Transactional
    fun find(reference: Reference<Widget>): Content<WidgetTransfer> {
        return widgetRepository.readByUUID(reference.uuid) pipe widgetMapper::export
    }
}