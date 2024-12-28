package de.ottorohenkohl.bloggin.person.domain.service.transfer

import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.person.domain.model.Person
import de.ottorohenkohl.bloggin.person.domain.annotation.value.Role

data class PermissionDTO(val person: Reference<Person>?, val role: Role?)