package dev.rohenkohl.bloggin.person.domain.service.transfer

import dev.rohenkohl.bloggin.person.domain.model.Person
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference

data class LoginDTO(val issuer: String?, val person: Reference<Person>?, val subject: String?)