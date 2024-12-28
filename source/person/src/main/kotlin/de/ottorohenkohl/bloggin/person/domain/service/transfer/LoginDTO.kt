package de.ottorohenkohl.bloggin.person.domain.service.transfer

import de.ottorohenkohl.bloggin.person.domain.model.Person
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference

data class LoginDTO(val issuer: String?, val person: Reference<Person>?, val subject: String?)