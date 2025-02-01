package dev.rohenkohl.bloggin.person.domain.service.transfer

import dev.rohenkohl.bloggin.person.domain.annotation.value.Role
import dev.rohenkohl.bloggin.person.domain.model.Person
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference

data class PermissionDTO(val person: Reference<Person>?, val role: Role?)