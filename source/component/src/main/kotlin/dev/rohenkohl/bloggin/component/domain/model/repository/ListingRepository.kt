package dev.rohenkohl.bloggin.component.domain.model.repository

import dev.rohenkohl.bloggin.component.domain.model.Listing
import dev.rohenkohl.bloggin.component.domain.model.Listing_
import dev.rohenkohl.bloggin.component.domain.model.Widget
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface ListingRepository : Repository<Listing> {

    @Find
    fun readByUUID(uuid: UUID): Listing

    fun readByWidget(widget: Widget): Listing {
        val builder = entityManager.criteriaBuilder
        val query = builder.createQuery(Listing::class.java)
        val root = query.from(Listing::class.java)

        query.select(root).where(builder.isMember(widget, root.get(Listing_.elements)))

        return entityManager.createQuery(query).singleResult
    }
}
