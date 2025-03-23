package dev.rohenkohl.bloggin.user.domain.service.mapper

import dev.rohenkohl.bloggin.user.domain.model.Permission
import dev.rohenkohl.bloggin.user.domain.model.repository.PermissionRepository
import dev.rohenkohl.bloggin.user.domain.service.transfer.PermissionTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class PermissionMapper(val personMapper: PersonMapper) : Exporter<PermissionTransfer, Permission>, Importer<PermissionTransfer, Permission>, Modifier<PermissionTransfer, Permission> {

    private lateinit var permissionRepository: PermissionRepository

    @Inject
    internal constructor(personMapper: PersonMapper, permissionRepository: PermissionRepository) : this(personMapper) {
        this.permissionRepository = permissionRepository
    }

    override fun export(identifiable: Permission): Content<PermissionTransfer> {
        val personReference = Reference(identifiable.person)
        val permissionTransfer = PermissionTransfer(identifiable.role, personReference)

        return Content(permissionTransfer, identifiable.uuid)
    }

    override fun import(transfer: PermissionTransfer): Permission {
        val person = personMapper.load(transfer.person.nonnull())

        return Permission(transfer.role.nonnull(), person) pipe permissionRepository::create
    }

    override fun modify(content: Content<PermissionTransfer>): Reference<Permission> {
        val authorization = permissionRepository.readByUUID(content.uuid)

        authorization.role = content.payload.role ?: authorization.role

        return Reference(authorization)
    }
}