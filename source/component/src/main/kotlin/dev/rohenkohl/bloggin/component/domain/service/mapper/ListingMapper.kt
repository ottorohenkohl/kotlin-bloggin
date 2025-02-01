package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Listing
import dev.rohenkohl.bloggin.component.domain.repository.ListingRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.ListingDTO
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class ListingMapper : Exporter<ListingDTO, Listing>, Importer<ListingDTO, Listing>, Modifier<ListingDTO, Listing> {

    @Inject
    private lateinit var widgetMapper: WidgetMapper

    @Inject
    private lateinit var listingRepository: ListingRepository

    override fun export(identifiable: Listing): Content<ListingDTO> {
        val widgetDTOs = identifiable.elements.map(widgetMapper::export)
        val listingDTO = ListingDTO(identifiable.across, identifiable.direction, widgetDTOs, identifiable.main)

        return Reference(listingDTO, identifiable.uuid)
    }

    override fun import(dto: ListingDTO): Listing {
        val listing = Listing(dto.across.nonnull(), dto.direction.nonnull(), dto.main.nonnull())

        return listingRepository.create(listing)
    }

    override fun modify(content: Content<ListingDTO>): Reference<Listing> {
        val listing = listingRepository.readByUUID(content.uuid)

        listing.direction = content.payload.direction ?: listing.direction
        listing.across = content.payload.across ?: listing.across
        listing.main = content.payload.main ?: listing.main

        return Reference(listing)
    }
}