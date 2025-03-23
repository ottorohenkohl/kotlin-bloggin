package dev.rohenkohl.bloggin.component.domain.model.repository

import dev.rohenkohl.bloggin.component.domain.model.Separator
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface SeparatorRepository : Repository<Separator> {

    @Find
    fun readByUUID(uuid: UUID): Separator
}
