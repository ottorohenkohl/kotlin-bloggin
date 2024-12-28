package de.ottorohenkohl.bloggin.settings.domain.service.mapper

import de.ottorohenkohl.bloggin.settings.domain.service.transfer.SeparatorDTO
import de.ottorohenkohl.bloggin.settings.domain.model.Separator
import de.ottorohenkohl.bloggin.settings.domain.repository.SeparatorRepository
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Exporter
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Importer
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Modifier
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.nonnull
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