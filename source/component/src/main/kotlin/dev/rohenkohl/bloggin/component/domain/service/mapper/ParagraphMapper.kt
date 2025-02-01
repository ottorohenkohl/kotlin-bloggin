package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Paragraph
import dev.rohenkohl.bloggin.component.domain.repository.ParagraphRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.ParagraphDTO
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
class ParagraphMapper : Exporter<ParagraphDTO, Paragraph>, Importer<ParagraphDTO, Paragraph>,
    Modifier<ParagraphDTO, Paragraph> {

    @Inject
    private lateinit var paragraphRepository: ParagraphRepository

    override fun export(identifiable: Paragraph): Content<ParagraphDTO> {
        val paragraphDTO = ParagraphDTO(identifiable.size, identifiable.text.value)

        return Reference(paragraphDTO, identifiable.uuid)
    }

    override fun import(dto: ParagraphDTO): Paragraph {
        val paragraph = Paragraph(dto.size.nonnull(), dto.text.nonnullText())

        return paragraphRepository.create(paragraph)
    }

    override fun modify(content: Content<ParagraphDTO>): Reference<Paragraph> {
        val header = paragraphRepository.readByUUID(content.uuid)

        header.size = content.payload.size ?: header.size
        header.text = content.payload.text?.nullableText() ?: header.text

        return Reference(header)
    }
}