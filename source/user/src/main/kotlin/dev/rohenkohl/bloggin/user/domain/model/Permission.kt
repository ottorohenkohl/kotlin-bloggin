package dev.rohenkohl.bloggin.user.domain.model

import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import jakarta.validation.Valid

@Entity
class Permission(

    @field:Valid
    var role: Role,

    @field:OneToOne
    @field:Valid
    var person: Person

) : Identifiable()