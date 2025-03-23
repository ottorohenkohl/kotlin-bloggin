package dev.rohenkohl.bloggin.page.domain.model

import dev.rohenkohl.bloggin.component.domain.model.Layout
import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import dev.rohenkohl.bloggin.zero.domain.validator.constraint.Key
import dev.rohenkohl.bloggin.zero.domain.validator.constraint.Text
import jakarta.persistence.CascadeType.REMOVE
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import jakarta.validation.Valid

@Entity
data class Site(

    @field:Text
    var name: String,

    @field:Column(unique = true)
    @field:Key
    var key: String,

    @field:OneToOne(cascade = [REMOVE])
    @field:Valid
    var layout: Layout

) : Identifiable()