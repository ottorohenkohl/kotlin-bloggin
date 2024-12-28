package de.ottorohenkohl.bloggin.settings.domain.service

import de.ottorohenkohl.bloggin.settings.domain.annotation.Owning
import de.ottorohenkohl.bloggin.settings.domain.model.Listing
import de.ottorohenkohl.bloggin.settings.domain.service.mapper.WidgetMapper
import de.ottorohenkohl.bloggin.settings.domain.service.transfer.WidgetDTO
import de.ottorohenkohl.bloggin.settings.domain.model.Widget
import de.ottorohenkohl.bloggin.settings.domain.repository.ListingRepository
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.extension.yield
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import java.util.*

@RequestScoped
class ListingService {

    @Inject
    private lateinit var listingRepository: ListingRepository

    @Inject
    private lateinit var widgetMapper: WidgetMapper

    @Owning
    @Transactional
    fun store(reference: Reference<Listing>, widgetDTO: WidgetDTO): Reference<Widget> {
        val listing = listingRepository.readByUUID(reference.uuid)
        val widget = widgetMapper.import(widgetDTO)

        return listing.elements.add(widget) yield Reference(widget)
    }

    @Owning
    @Transactional
    fun swap(reference: Reference<Listing>, first: Reference<Widget>, second: Reference<Widget>): Reference<Listing> {
        val listing = listingRepository.readByUUID(reference.uuid)

        val indexFirst = listing.elements.indexOfFirst { it.uuid == first.uuid }
        val indexSecond = listing.elements.indexOfFirst { it.uuid == second.uuid }

        if (indexFirst == -1 || indexSecond == -1) throw NotFoundException()

        return Collections.swap(listing.elements, indexFirst, indexSecond) yield reference
    }
}