package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Icon
import dev.rohenkohl.bloggin.component.domain.model.repository.IconRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.IconTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class IconMapper(): Exporter<IconTransfer, Icon>, Importer<IconTransfer, Icon>, Modifier<IconTransfer, Icon> {

    private lateinit var iconRepository: IconRepository

    @Inject
    internal constructor(iconRepository: IconRepository) : this() {
        this.iconRepository = iconRepository
    }

    override fun export(identifiable: Icon): Content<IconTransfer> {
        val iconTransfer = IconTransfer(identifiable.primeicon)

        return Content(iconTransfer, identifiable.uuid)
    }

    override fun import(transfer: IconTransfer): Icon {
        val icon = Icon(transfer.primeicon.nonnull())

        return iconRepository.create(icon)
    }

    override fun modify(content: Content<IconTransfer>): Reference<Icon> {
        val icon = iconRepository.readByUUID(content.uuid)

        icon.primeicon = content.payload.primeicon ?: icon.primeicon

        return Reference(icon)
    }
}