package dev.rohenkohl.bloggin.user.domain.service

import dev.rohenkohl.bloggin.user.domain.model.Permission
import dev.rohenkohl.bloggin.user.domain.model.Person
import dev.rohenkohl.bloggin.user.domain.model.repository.PermissionRepository
import dev.rohenkohl.bloggin.user.domain.service.mapper.PermissionMapper
import dev.rohenkohl.bloggin.user.domain.service.mapper.PersonMapper
import dev.rohenkohl.bloggin.user.domain.service.transfer.PermissionTransfer
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.control.ActivateRequestContext
import jakarta.inject.Inject
import jakarta.transaction.Transactional

@ActivateRequestContext
@ApplicationScoped
class PermissionService(val permissionMapper: PermissionMapper, val personMapper: PersonMapper) {

    private lateinit var permissionRepository: PermissionRepository

    @Inject
    internal constructor(permissionRepository: PermissionRepository, permissionMapper: PermissionMapper, personMapper: PersonMapper) : this(permissionMapper, personMapper) {
        this.permissionRepository = permissionRepository
    }

    @Transactional
    fun change(content: Content<PermissionTransfer>): Reference<Permission> {
        return permissionMapper.modify(content)
    }

    @Transactional
    fun find(reference: Reference<Person>): Content<PermissionTransfer> {
        return personMapper.load(reference) pipe permissionRepository::readByPerson pipe permissionMapper::export
    }

    @Transactional
    fun store(permissionTransfer: PermissionTransfer): Reference<Permission> {
        return permissionMapper.import(permissionTransfer) pipe { Reference(it) }
    }
}