package dev.rohenkohl.bloggin.component.domain.service

import dev.rohenkohl.bloggin.component.domain.annotation.Owning
import dev.rohenkohl.bloggin.component.domain.model.Widget
import dev.rohenkohl.bloggin.component.domain.repository.WidgetRepository
import dev.rohenkohl.bloggin.component.domain.service.mapper.WidgetMapper
import dev.rohenkohl.bloggin.component.domain.service.transfer.WidgetDTO
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@RequestScoped
class WidgetService {

    @Inject
    private lateinit var widgetMapper: WidgetMapper

    @Inject
    private lateinit var widgetRepository: WidgetRepository

    @Owning
    @Transactional
    fun change(content: Content<WidgetDTO>): Reference<Widget> {
        return widgetMapper.modify(content)
    }

    @Owning
    @Transactional
    fun erase(reference: Reference<Widget>): Reference<WidgetDTO> {
        return widgetRepository.readByUUID(reference.uuid) pipe widgetRepository::delete pipe widgetMapper::export
    }

    @Transactional
    fun find(reference: Reference<Widget>): Content<WidgetDTO> {
        return widgetRepository.readByUUID(reference.uuid) pipe widgetMapper::export
    }
}