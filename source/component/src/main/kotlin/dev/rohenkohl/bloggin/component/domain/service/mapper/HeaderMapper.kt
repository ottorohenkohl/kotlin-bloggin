package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Header
import dev.rohenkohl.bloggin.component.domain.model.repository.HeaderRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.HeaderTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class HeaderMapper(): Exporter<HeaderTransfer, Header>, Importer<HeaderTransfer, Header>, Modifier<HeaderTransfer, Header> {

    private lateinit var headerRepository: HeaderRepository

    @Inject
    internal constructor(headerRepository: HeaderRepository) : this() {
        this.headerRepository = headerRepository
    }

    override fun export(identifiable: Header): Content<HeaderTransfer> {
        val headerTransfer = HeaderTransfer(identifiable.level, identifiable.text)

        return Content(headerTransfer, identifiable.uuid)
    }

    override fun import(transfer: HeaderTransfer): Header {
        val header = Header(transfer.level.nonnull(), transfer.text.nonnull())

        return headerRepository.create(header)
    }

    override fun modify(content: Content<HeaderTransfer>): Reference<Header> {
        val header = headerRepository.readByUUID(content.uuid)

        header.level = content.payload.level ?: header.level
        header.text = content.payload.text ?: header.text

        return Reference(header)
    }
}