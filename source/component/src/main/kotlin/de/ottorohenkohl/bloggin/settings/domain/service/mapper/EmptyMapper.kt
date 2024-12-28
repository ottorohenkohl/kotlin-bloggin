package de.ottorohenkohl.bloggin.settings.domain.service.mapper

import de.ottorohenkohl.bloggin.settings.domain.model.Widget
import de.ottorohenkohl.bloggin.settings.domain.service.transfer.EmptyDTO
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Exporter
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class EmptyMapper : Exporter<EmptyDTO, Widget> {

    override fun export(identifiable: Widget): Content<EmptyDTO> {
        return Reference(EmptyDTO(identifiable::class.java), identifiable.uuid)
    }
}