package de.ottorohenkohl.bloggin.person.domain.model

import de.ottorohenkohl.bloggin.person.domain.model.value.Issuer
import de.ottorohenkohl.bloggin.person.domain.model.value.Subject
import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.validation.Valid

@Entity
class Login(@Valid var issuer: Issuer, @OneToOne @Valid var person: Person, @Valid var subject: Subject) : Identifiable()