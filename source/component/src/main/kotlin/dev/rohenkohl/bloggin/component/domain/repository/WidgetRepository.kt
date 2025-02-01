package dev.rohenkohl.bloggin.component.domain.repository

import dev.rohenkohl.bloggin.component.domain.model.Widget
import dev.rohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface WidgetRepository : Repository<Widget> {

    @Find
    override fun readByUUID(uuid: UUID): Widget
}
