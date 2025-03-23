package dev.rohenkohl.bloggin.user.domain.model

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import dev.rohenkohl.bloggin.zero.domain.validator.constraint.Key
import jakarta.persistence.Entity
import jakarta.validation.constraints.Email

@Entity
class Person(

    @field:Email
    var mail: String,

    @field:Key
    var nickname: String

) : Identifiable()