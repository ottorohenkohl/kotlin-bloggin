package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Separator
import dev.rohenkohl.bloggin.component.domain.repository.SeparatorRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.SeparatorDTO
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class SeparatorMapper : Exporter<SeparatorDTO, Separator>, Importer<SeparatorDTO, Separator>,
    Modifier<SeparatorDTO, Separator> {

    @Inject
    private lateinit var separatorRepository: SeparatorRepository

    override fun export(identifiable: Separator): Content<SeparatorDTO> {
        val separatorDTO = SeparatorDTO(identifiable.direction)

        return Reference(separatorDTO, identifiable.uuid)
    }

    override fun import(dto: SeparatorDTO): Separator {
        val separator = Separator(dto.direction.nonnull())

        return separatorRepository.create(separator)
    }

    override fun modify(content: Content<SeparatorDTO>): Reference<Separator> {
        val separator = separatorRepository.readByUUID(content.uuid)

        separator.direction = content.payload.direction ?: separator.direction

        return Reference(separator)
    }
}