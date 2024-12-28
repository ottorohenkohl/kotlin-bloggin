package de.ottorohenkohl.bloggin.settings.domain.repository

import de.ottorohenkohl.bloggin.settings.domain.model.Paragraph
import de.ottorohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface ParagraphRepository : Repository<Paragraph> {

    @Find
    override fun readByUUID(uuid: UUID): Paragraph
}
