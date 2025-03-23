package dev.rohenkohl.bloggin.user.domain.model.repository

import dev.rohenkohl.bloggin.user.domain.model.Person
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import org.hibernate.query.Page
import java.util.*

internal interface PersonRepository : Repository<Person> {

    @Find
    fun readByNickname(nickname: String): Person

    @Find
    fun readByPage(page: Page): List<Person>

    @Find
    fun readByUUID(uuid: UUID): Person
}