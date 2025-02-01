package dev.rohenkohl.bloggin.component.domain.repository

import dev.rohenkohl.bloggin.component.domain.model.Separator
import dev.rohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface SeparatorRepository : Repository<Separator> {

    @Find
    override fun readByUUID(uuid: UUID): Separator
}
