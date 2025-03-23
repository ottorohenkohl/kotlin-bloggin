package dev.rohenkohl.bloggin.user.domain.service.transfer

import dev.rohenkohl.bloggin.user.domain.model.Person
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference

data class LoginTransfer(val issuer: String?, val subject: String?, val person: Reference<Person>?)