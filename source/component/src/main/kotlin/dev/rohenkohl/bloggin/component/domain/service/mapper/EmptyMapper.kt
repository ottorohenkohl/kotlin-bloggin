package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Widget
import dev.rohenkohl.bloggin.component.domain.service.transfer.EmptyDTO
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class EmptyMapper : Exporter<EmptyDTO, Widget> {

    override fun export(identifiable: Widget): Content<EmptyDTO> {
        return Reference(EmptyDTO(identifiable::class.java), identifiable.uuid)
    }
}