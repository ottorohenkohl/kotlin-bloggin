package de.ottorohenkohl.bloggin.settings.domain.repository

import de.ottorohenkohl.bloggin.settings.domain.model.Separator
import de.ottorohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface SeparatorRepository : Repository<Separator> {

    @Find
    override fun readByUUID(uuid: UUID): Separator
}
