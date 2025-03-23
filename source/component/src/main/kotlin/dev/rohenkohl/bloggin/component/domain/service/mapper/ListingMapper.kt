package dev.rohenkohl.bloggin.component.domain.service.mapper

import dev.rohenkohl.bloggin.component.domain.model.Listing
import dev.rohenkohl.bloggin.component.domain.model.repository.ListingRepository
import dev.rohenkohl.bloggin.component.domain.service.transfer.ListingTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class ListingMapper(val widgetMapper: WidgetMapper): Exporter<ListingTransfer, Listing>, Importer<ListingTransfer, Listing>, Modifier<ListingTransfer, Listing> {

    private lateinit var listingRepository: ListingRepository

    @Inject
    internal constructor(listingRepository: ListingRepository, widgetMapper: WidgetMapper) : this(widgetMapper) {
        this.listingRepository = listingRepository
    }

    override fun export(identifiable: Listing): Content<ListingTransfer> {
        val widgetReferences = identifiable.elements.map(widgetMapper::export)
        val listingTransfer = ListingTransfer(identifiable.across, identifiable.direction, identifiable.main, widgetReferences)

        return Content(listingTransfer, identifiable.uuid)
    }

    override fun import(transfer: ListingTransfer): Listing {
        val listing = Listing(transfer.across.nonnull(), transfer.direction.nonnull(), transfer.main.nonnull())

        return listingRepository.create(listing)
    }

    override fun modify(content: Content<ListingTransfer>): Reference<Listing> {
        val listing = listingRepository.readByUUID(content.uuid)

        listing.direction = content.payload.direction ?: listing.direction
        listing.across = content.payload.across ?: listing.across
        listing.main = content.payload.main ?: listing.main

        return Reference(listing)
    }
}