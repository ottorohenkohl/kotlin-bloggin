package de.ottorohenkohl.bloggin.settings.domain.repository

import de.ottorohenkohl.bloggin.settings.domain.model.Image
import de.ottorohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface ImageRepository : Repository<Image> {

    @Find
    override fun readByUUID(uuid: UUID): Image
}
