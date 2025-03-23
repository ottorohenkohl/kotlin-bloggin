package dev.rohenkohl.bloggin.page.domain.service.mapper

import dev.rohenkohl.bloggin.page.domain.model.Site
import dev.rohenkohl.bloggin.page.domain.service.transfer.PreviewTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PreviewMapper : Exporter<PreviewTransfer, Site> {

    override fun export(identifiable: Site): Content<PreviewTransfer> {
        val previewTransfer = PreviewTransfer(identifiable.name, identifiable.key)

        return Content(previewTransfer, identifiable.uuid)
    }
}