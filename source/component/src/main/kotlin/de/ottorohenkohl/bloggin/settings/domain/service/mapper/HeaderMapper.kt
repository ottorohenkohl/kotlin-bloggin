package de.ottorohenkohl.bloggin.settings.domain.service.mapper

import de.ottorohenkohl.bloggin.settings.domain.service.transfer.HeaderDTO
import de.ottorohenkohl.bloggin.settings.domain.model.Header
import de.ottorohenkohl.bloggin.settings.domain.repository.HeaderRepository
import de.ottorohenkohl.bloggin.settings.extension.nonnullText
import de.ottorohenkohl.bloggin.settings.extension.nullableText
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Exporter
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Importer
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Modifier
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class HeaderMapper : Exporter<HeaderDTO, Header>, Importer<HeaderDTO, Header>, Modifier<HeaderDTO, Header> {

    @Inject
    private lateinit var headerRepository: HeaderRepository

    override fun export(identifiable: Header): Content<HeaderDTO> {
        val header = HeaderDTO(identifiable.level, identifiable.text.value)

        return Reference(header, identifiable.uuid)
    }

    override fun import(dto: HeaderDTO): Header {
        val header = Header(dto.level.nonnull(), dto.text.nonnullText())

        return headerRepository.create(header)
    }

    override fun modify(content: Content<HeaderDTO>): Reference<Header> {
        val header = headerRepository.readByUUID(content.uuid)

        header.level = content.payload.level ?: header.level
        header.text = content.payload.text.nullableText() ?: header.text

        return Reference(header)
    }
}