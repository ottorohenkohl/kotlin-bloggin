package dev.rohenkohl.bloggin.user.domain.service.transfer

import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import dev.rohenkohl.bloggin.user.domain.model.Person
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference

data class PermissionTransfer(val role: Role?, val person: Reference<Person>?)