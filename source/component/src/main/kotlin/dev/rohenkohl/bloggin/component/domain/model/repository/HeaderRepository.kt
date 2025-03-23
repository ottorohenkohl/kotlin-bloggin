package dev.rohenkohl.bloggin.component.domain.model.repository

import dev.rohenkohl.bloggin.component.domain.model.Header
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface HeaderRepository : Repository<Header> {

    @Find
    fun readByUUID(uuid: UUID): Header
}
