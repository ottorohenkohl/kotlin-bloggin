package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.*
import dev.rohenkohl.bloggin.component.domain.model.Widget.Visitor
import dev.rohenkohl.bloggin.component.domain.service.transfer.*
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
internal class WidgetVisitor(
    private val emptyMapper: EmptyMapper,
    private val headerMapper: HeaderMapper,
    private val iconMapper: IconMapper,
    private val listingMapper: ListingMapper,
    private val paragraphMapper: ParagraphMapper,
    private val separatorMapper: SeparatorMapper,
) : Visitor<Content<WidgetDTO>> {

    override fun visit(header: Header): Content<HeaderDTO> {
        return headerMapper.export(header)
    }

    override fun visit(icon: Icon): Content<WidgetDTO> {
        return iconMapper.export(icon)
    }

    override fun visit(image: Image): Content<EmptyDTO> {
        return emptyMapper.export(image)
    }

    override fun visit(listing: Listing): Content<ListingDTO> {
        return listingMapper.export(listing)
    }

    override fun visit(paragraph: Paragraph): Content<ParagraphDTO> {
        return paragraphMapper.export(paragraph)
    }

    override fun visit(separator: Separator): Content<SeparatorDTO> {
        return separatorMapper.export(separator)
    }
}
