package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.validation.Valid

@Entity
class Ownership(@ManyToOne @Valid var layout: Layout, @ManyToOne @Valid var widget: Widget) : Identifiable()