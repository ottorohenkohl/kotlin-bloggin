package de.ottorohenkohl.bloggin.person.domain.repository

import de.ottorohenkohl.bloggin.person.domain.model.Person
import de.ottorohenkohl.bloggin.zero.domain.model.value.Name
import de.ottorohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import org.hibernate.query.Page
import java.util.*

internal interface PersonRepository : Repository<Person> {

    @Find
    fun readByPage(page: Page): List<Person>

    @Find
    override fun readByUUID(uuid: UUID): Person
}
