package de.ottorohenkohl.bloggin.person.domain.service

import de.ottorohenkohl.bloggin.person.domain.service.mapper.PermissionMapper
import de.ottorohenkohl.bloggin.person.domain.service.transfer.PermissionDTO
import de.ottorohenkohl.bloggin.person.domain.model.Permission
import de.ottorohenkohl.bloggin.person.domain.model.Person
import de.ottorohenkohl.bloggin.person.domain.repository.PermissionRepository
import de.ottorohenkohl.bloggin.person.domain.repository.PersonRepository
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.control.ActivateRequestContext
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ActivateRequestContext
@ApplicationScoped
class PermissionService {

    @Inject
    private lateinit var permissionMapper: PermissionMapper

    @Inject
    private lateinit var permissionRepository: PermissionRepository

    @Inject
    private lateinit var personRepository: PersonRepository

    @Transactional
    fun change(content: Content<PermissionDTO>): Reference<Permission> {
        return permissionMapper.modify(content)
    }

    @Transactional
    fun find(reference: Reference<Person>): Content<PermissionDTO> {
        return personRepository.readByUUID(reference.uuid) pipe permissionRepository::readByPerson pipe permissionMapper::export
    }
}