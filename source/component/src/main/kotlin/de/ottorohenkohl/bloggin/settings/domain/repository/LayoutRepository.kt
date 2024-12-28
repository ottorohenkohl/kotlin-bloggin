package de.ottorohenkohl.bloggin.settings.domain.repository

import de.ottorohenkohl.bloggin.settings.domain.model.Layout
import de.ottorohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface LayoutRepository : Repository<Layout> {

    @Find
    override fun readByUUID(uuid: UUID): Layout
}
