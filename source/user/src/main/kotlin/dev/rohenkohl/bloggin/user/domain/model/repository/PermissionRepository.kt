package dev.rohenkohl.bloggin.user.domain.model.repository

import dev.rohenkohl.bloggin.user.domain.model.Permission
import dev.rohenkohl.bloggin.user.domain.model.Person
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface PermissionRepository : Repository<Permission> {

    @Find
    fun readByPerson(person: Person): Permission

    @Find
    fun readByUUID(uuid: UUID): Permission
}
