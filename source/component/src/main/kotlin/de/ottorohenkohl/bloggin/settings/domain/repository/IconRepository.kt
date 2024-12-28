package de.ottorohenkohl.bloggin.settings.domain.repository

import de.ottorohenkohl.bloggin.settings.domain.model.Icon
import de.ottorohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface IconRepository : Repository<Icon> {

    @Find
    override fun readByUUID(uuid: UUID): Icon
}
