package de.ottorohenkohl.bloggin.settings.domain.service.mapper

import de.ottorohenkohl.bloggin.settings.domain.service.transfer.IconDTO
import de.ottorohenkohl.bloggin.settings.domain.model.Icon
import de.ottorohenkohl.bloggin.settings.domain.repository.IconRepository
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Exporter
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Importer
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Modifier
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class IconMapper : Exporter<IconDTO, Icon>, Importer<IconDTO, Icon>, Modifier<IconDTO, Icon> {

    @Inject
    private lateinit var iconRepository: IconRepository

    override fun export(identifiable: Icon): Content<IconDTO> {
        val icon = IconDTO(identifiable.primeicon)

        return Reference(icon, identifiable.uuid)
    }

    override fun import(dto: IconDTO): Icon {
        val icon = Icon(dto.primeicon.nonnull())

        return iconRepository.create(icon)
    }

    override fun modify(content: Content<IconDTO>): Reference<Icon> {
        val icon = iconRepository.readByUUID(content.uuid)

        icon.primeicon = content.payload.primeicon ?: icon.primeicon

        return Reference(icon)
    }
}