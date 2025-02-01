package dev.rohenkohl.bloggin.person.domain.model

import dev.rohenkohl.bloggin.person.domain.model.value.Issuer
import dev.rohenkohl.bloggin.person.domain.model.value.Subject
import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import jakarta.validation.Valid

@Entity
class Login(@Valid var issuer: Issuer, @OneToOne @Valid var person: Person, @Valid var subject: Subject) : Identifiable()