package de.ottorohenkohl.bloggin.person.domain.model

import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable
import de.ottorohenkohl.bloggin.person.domain.annotation.value.Role
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.validation.Valid

@Entity
class Permission(@Valid var role: Role, @OneToOne @Valid var person: Person) : Identifiable()