package dev.rohenkohl.bloggin.component.domain.service

import dev.rohenkohl.bloggin.component.domain.model.Layout
import dev.rohenkohl.bloggin.component.domain.model.Widget
import dev.rohenkohl.bloggin.component.domain.model.repository.LayoutRepository
import dev.rohenkohl.bloggin.component.domain.model.repository.WidgetRepository
import dev.rohenkohl.bloggin.component.domain.service.mapper.LayoutMapper
import dev.rohenkohl.bloggin.component.domain.service.mapper.WidgetMapper
import dev.rohenkohl.bloggin.component.domain.service.transfer.LayoutTransfer
import dev.rohenkohl.bloggin.component.domain.service.transfer.WidgetTransfer
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@RequestScoped
class LayoutService(private val layoutMapper: LayoutMapper, private val widgetMapper: WidgetMapper) {

    private lateinit var layoutRepository: LayoutRepository
    private lateinit var widgetRepository: WidgetRepository

    @Inject
    internal constructor(layoutMapper: LayoutMapper, layoutRepository: LayoutRepository, widgetMapper: WidgetMapper, widgetRepository: WidgetRepository) : this(layoutMapper, widgetMapper) {
        this.layoutRepository = layoutRepository
        this.widgetRepository = widgetRepository
    }

    @Transactional
    fun find(reference: Reference<Widget>): Content<LayoutTransfer> {
        return widgetRepository.readByUUID(reference.uuid) pipe layoutRepository::readByWidget pipe layoutMapper::export
    }

    @Transactional
    fun store(widgetTransfer: WidgetTransfer, role: Role): Reference<Layout> {
        val widget = widgetMapper.import(widgetTransfer)
        val layout = layoutRepository.create(Layout(role, true, widget))

        layout.owning.add(widget)

        return Reference(layout)
    }
}