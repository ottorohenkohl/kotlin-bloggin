package dev.rohenkohl.bloggin.component.domain.repository

import dev.rohenkohl.bloggin.component.domain.model.Listing
import dev.rohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface ListingRepository : Repository<Listing> {

    @Find
    override fun readByUUID(uuid: UUID): Listing
}
