package dev.rohenkohl.bloggin.person.domain.repository

import dev.rohenkohl.bloggin.person.domain.model.Permission
import dev.rohenkohl.bloggin.person.domain.model.Person
import dev.rohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface PermissionRepository : Repository<Permission> {

    @Find
    fun readByPerson(person: Person): Permission

    @Find
    override fun readByUUID(uuid: UUID): Permission
}
