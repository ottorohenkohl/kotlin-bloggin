package dev.rohenkohl.bloggin.component.domain.repository

import dev.rohenkohl.bloggin.component.domain.model.Icon
import dev.rohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface IconRepository : Repository<Icon> {

    @Find
    override fun readByUUID(uuid: UUID): Icon
}
