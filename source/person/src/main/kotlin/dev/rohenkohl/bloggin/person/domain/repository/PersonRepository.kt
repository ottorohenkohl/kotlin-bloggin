package dev.rohenkohl.bloggin.person.domain.repository

import dev.rohenkohl.bloggin.person.domain.model.Person
import dev.rohenkohl.bloggin.zero.domain.model.value.Name
import dev.rohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import org.hibernate.query.Page
import java.util.*

internal interface PersonRepository : Repository<Person> {

    @Find
    fun readByPage(page: Page): List<Person>

    @Find
    fun readByUsername(username: Name): Person

    @Find
    override fun readByUUID(uuid: UUID): Person
}
