package dev.rohenkohl.bloggin.user.domain.model

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import dev.rohenkohl.bloggin.zero.domain.validator.constraint.Key
import jakarta.persistence.CascadeType.REMOVE
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.validation.Valid
import org.hibernate.validator.constraints.URL

@Entity
class Login(

    @field:URL
    var issuer: String,

    @field:Key
    var subject: String,

    @field:OneToOne
    @field:Valid
    var person: Person

) : Identifiable()