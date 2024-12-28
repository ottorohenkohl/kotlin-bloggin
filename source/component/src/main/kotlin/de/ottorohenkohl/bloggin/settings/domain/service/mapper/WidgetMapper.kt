package de.ottorohenkohl.bloggin.settings.domain.service.mapper

import de.ottorohenkohl.bloggin.settings.domain.model.Widget
import de.ottorohenkohl.bloggin.settings.domain.service.transfer.*
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Exporter
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Importer
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Modifier
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.BadRequestException

@ApplicationScoped
class WidgetMapper : Exporter<WidgetDTO, Widget>, Importer<WidgetDTO, Widget>, Modifier<WidgetDTO, Widget> {

    @Inject
    private lateinit var headerMapper: HeaderMapper

    @Inject
    private lateinit var iconMapper: IconMapper

    @Inject
    private lateinit var imageMapper: ImageMapper

    @Inject
    private lateinit var listingMapper: ListingMapper

    @Inject
    private lateinit var paragraphMapper: ParagraphMapper

    @Inject
    private lateinit var separatorMapper: SeparatorMapper

    @Inject
    private lateinit var widgetVisitor: WidgetVisitor

    override fun export(identifiable: Widget): Content<WidgetDTO> {
        return identifiable.accept(widgetVisitor)
    }

    override fun import(dto: WidgetDTO): Widget {
        return when (dto) {
            is HeaderDTO -> headerMapper.import(dto)
            is IconDTO -> iconMapper.import(dto)
            is ImageDTO -> imageMapper.import(dto)
            is ListingDTO -> listingMapper.import(dto)
            is ParagraphDTO -> paragraphMapper.import(dto)
            is SeparatorDTO -> separatorMapper.import(dto)

            is EmptyDTO -> throw BadRequestException("Empty widget cannot be imported")
        }
    }

    override fun modify(content: Content<WidgetDTO>): Reference<Widget> {
        return when (val item = content.payload) {
            is HeaderDTO -> headerMapper.modify(Reference(item, content.uuid))
            is IconDTO -> iconMapper.modify(Reference(item, content.uuid))
            is ListingDTO -> listingMapper.modify(Reference(item, content.uuid))
            is ParagraphDTO -> paragraphMapper.modify(Reference(item, content.uuid))
            is SeparatorDTO -> separatorMapper.modify(Reference(item, content.uuid))

            is EmptyDTO -> throw BadRequestException("Empty widget cannot be modified")
            is ImageDTO -> throw BadRequestException("Image widget cannot be modified")
        }
    }
}