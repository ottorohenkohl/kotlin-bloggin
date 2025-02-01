package dev.rohenkohl.bloggin.person.domain.service.mapper

import dev.rohenkohl.bloggin.person.domain.model.Permission
import dev.rohenkohl.bloggin.person.domain.repository.PermissionRepository
import dev.rohenkohl.bloggin.person.domain.service.transfer.PermissionDTO
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Modifier
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class PermissionMapper : Exporter<PermissionDTO, Permission>, Modifier<PermissionDTO, Permission> {

    @Inject
    private lateinit var permissionRepository: PermissionRepository

    override fun export(identifiable: Permission): Content<PermissionDTO> {
        val personDTO = Reference(identifiable.person)
        val permissionDTO = PermissionDTO(personDTO, identifiable.role)

        return Reference(permissionDTO, identifiable.uuid)
    }

    override fun modify(content: Content<PermissionDTO>): Reference<Permission> {
        val authorization = permissionRepository.readByUUID(content.uuid)

        authorization.role = content.payload.role ?: authorization.role

        return Reference(authorization)
    }
}