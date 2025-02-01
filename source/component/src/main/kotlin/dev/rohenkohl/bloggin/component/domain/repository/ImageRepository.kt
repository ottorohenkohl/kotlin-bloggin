package dev.rohenkohl.bloggin.component.domain.repository

import dev.rohenkohl.bloggin.component.domain.model.Image
import dev.rohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface ImageRepository : Repository<Image> {

    @Find
    override fun readByUUID(uuid: UUID): Image
}
