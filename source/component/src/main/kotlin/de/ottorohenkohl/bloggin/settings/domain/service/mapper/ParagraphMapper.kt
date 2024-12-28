package de.ottorohenkohl.bloggin.settings.domain.service.mapper

import de.ottorohenkohl.bloggin.settings.domain.service.transfer.ParagraphDTO
import de.ottorohenkohl.bloggin.settings.domain.model.Paragraph
import de.ottorohenkohl.bloggin.settings.domain.repository.ParagraphRepository
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