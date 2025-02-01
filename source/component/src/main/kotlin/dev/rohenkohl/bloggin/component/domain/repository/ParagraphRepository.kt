package dev.rohenkohl.bloggin.component.domain.repository

import dev.rohenkohl.bloggin.component.domain.model.Paragraph
import dev.rohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface ParagraphRepository : Repository<Paragraph> {

    @Find
    override fun readByUUID(uuid: UUID): Paragraph
}
