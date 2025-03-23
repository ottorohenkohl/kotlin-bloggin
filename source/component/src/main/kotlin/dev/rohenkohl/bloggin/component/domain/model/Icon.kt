package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.component.domain.model.constant.Primeicon
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Icon(

    @field:Valid
    var primeicon: Primeicon


) : Widget()