package de.ottorohenkohl.bloggin.person.domain.model

import de.ottorohenkohl.bloggin.zero.domain.model.value.Name
import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Person(@Valid var nickname: Name, @Valid var username: Name) : Identifiable()