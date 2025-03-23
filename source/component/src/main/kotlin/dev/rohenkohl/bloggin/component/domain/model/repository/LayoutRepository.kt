package dev.rohenkohl.bloggin.component.domain.model.repository

import dev.rohenkohl.bloggin.component.domain.model.Layout
import dev.rohenkohl.bloggin.component.domain.model.Layout_
import dev.rohenkohl.bloggin.component.domain.model.Widget
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface LayoutRepository : Repository<Layout> {

    @Find
    fun readByUUID(uuid: UUID): Layout

    fun readByWidget(widget: Widget): Layout {
        val builder = entityManager.criteriaBuilder
        val query = builder.createQuery(Layout::class.java)
        val root = query.from(Layout::class.java)

        query.select(root).where(builder.isMember(widget, root.get(Layout_.owning)))

        return entityManager.createQuery(query).singleResult
    }
}