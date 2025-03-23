package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Separator
import dev.rohenkohl.bloggin.component.domain.model.repository.SeparatorRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.SeparatorTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class SeparatorMapper(): Exporter<SeparatorTransfer, Separator>, Importer<SeparatorTransfer, Separator>, Modifier<SeparatorTransfer, Separator> {

    private lateinit var separatorRepository: SeparatorRepository

    @Inject
    internal constructor(separatorRepository: SeparatorRepository): this() {
        this.separatorRepository = separatorRepository
    }

    override fun export(identifiable: Separator): Content<SeparatorTransfer> {
        val separatorTransfer = SeparatorTransfer(identifiable.direction)

        return Content(separatorTransfer, identifiable.uuid)
    }

    override fun import(transfer: SeparatorTransfer): Separator {
        val separator = Separator(transfer.direction.nonnull())

        return separatorRepository.create(separator)
    }

    override fun modify(content: Content<SeparatorTransfer>): Reference<Separator> {
        val separator = separatorRepository.readByUUID(content.uuid)

        separator.direction = content.payload.direction ?: separator.direction

        return Reference(separator)
    }
}