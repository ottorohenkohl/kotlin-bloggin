package dev.rohenkohl.bloggin.person.domain.service

import dev.rohenkohl.bloggin.person.domain.model.Permission
import dev.rohenkohl.bloggin.person.domain.model.Person
import dev.rohenkohl.bloggin.person.domain.repository.PermissionRepository
import dev.rohenkohl.bloggin.person.domain.repository.PersonRepository
import dev.rohenkohl.bloggin.person.domain.service.mapper.PermissionMapper
import dev.rohenkohl.bloggin.person.domain.service.transfer.PermissionDTO
import dev.rohenkohl.bloggin.person.extension.PersonExistsException
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import dev.rohenkohl.bloggin.zero.extension.nonnullName
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.control.ActivateRequestContext
import jakarta.inject.Inject
import jakarta.persistence.NoResultException
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