package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.person.domain.annotation.value.Role
import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.validation.Valid

@Entity
class Layout(@Valid var role: Role, @ManyToOne var widget: Widget) : Identifiable()