package dev.rohenkohl.bloggin.page.domain.model

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.validation.Valid

@Entity
data class Sitemap(

    @field:ManyToOne
    @field:Valid
    var landing: Site,

) : Identifiable()