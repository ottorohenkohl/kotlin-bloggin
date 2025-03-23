package dev.rohenkohl.bloggin.component.domain.model.repository

import dev.rohenkohl.bloggin.component.domain.model.Paragraph
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface ParagraphRepository : Repository<Paragraph> {

    @Find
    fun readByUUID(uuid: UUID): Paragraph
}
