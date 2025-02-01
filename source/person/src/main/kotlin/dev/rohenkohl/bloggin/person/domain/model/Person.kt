package dev.rohenkohl.bloggin.person.domain.model

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import dev.rohenkohl.bloggin.zero.domain.model.value.Name
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Person(@Valid var nickname: Name, @Valid var username: Name) : Identifiable()