package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Header
import dev.rohenkohl.bloggin.component.domain.repository.HeaderRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.HeaderDTO
import dev.rohenkohl.bloggin.component.extension.nonnullText
import dev.rohenkohl.bloggin.component.extension.nullableText
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
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