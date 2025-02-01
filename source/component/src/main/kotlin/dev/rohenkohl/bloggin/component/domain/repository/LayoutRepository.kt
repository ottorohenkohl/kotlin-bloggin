package dev.rohenkohl.bloggin.component.domain.repository

import dev.rohenkohl.bloggin.component.domain.model.Layout
import dev.rohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface LayoutRepository : Repository<Layout> {

    @Find
    override fun readByUUID(uuid: UUID): Layout
}
