package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Paragraph
import dev.rohenkohl.bloggin.component.domain.model.repository.ParagraphRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.ParagraphTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class ParagraphMapper() : Exporter<ParagraphTransfer, Paragraph>, Importer<ParagraphTransfer, Paragraph>, Modifier<ParagraphTransfer, Paragraph> {

    private lateinit var paragraphRepository: ParagraphRepository

    @Inject
    internal constructor(paragraphRepository: ParagraphRepository) : this() {
        this.paragraphRepository = paragraphRepository
    }

    override fun export(identifiable: Paragraph): Content<ParagraphTransfer> {
        val paragraphTransfer = ParagraphTransfer(identifiable.fontsize, identifiable.text)

        return Content(paragraphTransfer, identifiable.uuid)
    }

    override fun import(transfer: ParagraphTransfer): Paragraph {
        val paragraph = Paragraph(transfer.fontsize.nonnull(), transfer.text.nonnull())

        return paragraphRepository.create(paragraph)
    }

    override fun modify(content: Content<ParagraphTransfer>): Reference<Paragraph> {
        val header = paragraphRepository.readByUUID(content.uuid)

        header.fontsize = content.payload.fontsize ?: header.fontsize
        header.text = content.payload.text ?: header.text

        return Reference(header)
    }
}