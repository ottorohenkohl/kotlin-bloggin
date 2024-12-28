package de.ottorohenkohl.bloggin.settings.domain.repository

import de.ottorohenkohl.bloggin.settings.domain.model.Ownership
import de.ottorohenkohl.bloggin.settings.domain.model.Widget
import de.ottorohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface OwnershipRepository : Repository<Ownership> {

    @Find
    fun readByWidget(widget: Widget): Ownership

    @Find
    override fun readByUUID(uuid: UUID): Ownership
}
