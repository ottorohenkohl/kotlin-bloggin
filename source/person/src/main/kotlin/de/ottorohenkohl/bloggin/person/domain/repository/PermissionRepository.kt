package de.ottorohenkohl.bloggin.person.domain.repository

import de.ottorohenkohl.bloggin.person.domain.model.Permission
import de.ottorohenkohl.bloggin.person.domain.model.Person
import de.ottorohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface PermissionRepository : Repository<Permission> {

    @Find
    fun readByPerson(person: Person): Permission

    @Find
    override fun readByUUID(uuid: UUID): Permission
}
