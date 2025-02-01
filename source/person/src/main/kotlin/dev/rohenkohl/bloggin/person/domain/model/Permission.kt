package dev.rohenkohl.bloggin.person.domain.model

import dev.rohenkohl.bloggin.person.domain.annotation.value.Role
import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.validation.Valid

@Entity
class Permission(@Valid var role: Role, @OneToOne @Valid var person: Person) : Identifiable()