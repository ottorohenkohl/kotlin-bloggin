package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.*
import dev.rohenkohl.bloggin.component.domain.service.transfer.*
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.InternalServerErrorException

@ApplicationScoped
class WidgetMapper internal constructor(

    private val headerMapper: HeaderMapper,
    private val iconMapper: IconMapper,
    private val imageMapper: ImageMapper,
    private val listingMapper: ListingMapper,
    private val paragraphMapper: ParagraphMapper,
    private val separatorMapper: SeparatorMapper

) : Exporter<WidgetTransfer, Widget>, Importer<WidgetTransfer, Widget>, Modifier<WidgetTransfer, Widget> {

    override fun export(identifiable: Widget): Content<WidgetTransfer> {
        return when (identifiable) {
            is Header -> headerMapper.export(identifiable)
            is Icon -> iconMapper.export(identifiable)
            is Image -> imageMapper.export(identifiable)
            is Listing -> listingMapper.export(identifiable)
            is Paragraph -> paragraphMapper.export(identifiable)
            is Separator -> separatorMapper.export(identifiable)

            else -> throw InternalServerErrorException("Oops, the image is not registered yet")
        }
    }

    override fun import(transfer: WidgetTransfer): Widget {
        return when (transfer) {
            is HeaderTransfer -> headerMapper.import(transfer)
            is IconTransfer -> iconMapper.import(transfer)
            is ImageTransfer -> imageMapper.import(transfer)
            is ListingTransfer -> listingMapper.import(transfer)
            is ParagraphTransfer -> paragraphMapper.import(transfer)
            is SeparatorTransfer -> separatorMapper.import(transfer)
        }
    }

    override fun modify(content: Content<WidgetTransfer>): Reference<Widget> {
        return when (val item = content.payload) {
            is HeaderTransfer -> headerMapper.modify(Content(item, content.uuid))
            is IconTransfer -> iconMapper.modify(Content(item, content.uuid))
            is ListingTransfer -> listingMapper.modify(Content(item, content.uuid))
            is ParagraphTransfer -> paragraphMapper.modify(Content(item, content.uuid))
            is SeparatorTransfer -> separatorMapper.modify(Content(item, content.uuid))

            is ImageTransfer -> throw BadRequestException("Image widget cannot be modified")
        }
    }
}