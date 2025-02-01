package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Icon
import dev.rohenkohl.bloggin.component.domain.repository.IconRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.IconDTO
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
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