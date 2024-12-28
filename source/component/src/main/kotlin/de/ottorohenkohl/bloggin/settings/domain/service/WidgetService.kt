package de.ottorohenkohl.bloggin.settings.domain.service

import de.ottorohenkohl.bloggin.settings.domain.annotation.Owning
import de.ottorohenkohl.bloggin.settings.domain.service.mapper.WidgetMapper
import de.ottorohenkohl.bloggin.settings.domain.service.transfer.WidgetDTO
import de.ottorohenkohl.bloggin.settings.domain.model.Widget
import de.ottorohenkohl.bloggin.settings.domain.repository.WidgetRepository
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.pipe
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