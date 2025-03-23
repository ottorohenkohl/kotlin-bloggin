package dev.rohenkohl.bloggin.component.domain.model.repository

import dev.rohenkohl.bloggin.component.domain.model.Widget
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface WidgetRepository : Repository<Widget> {

    @Find
    fun readByUUID(uuid: UUID): Widget
}
