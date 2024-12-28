package de.ottorohenkohl.bloggin.settings.domain.model

import de.ottorohenkohl.bloggin.person.domain.annotation.value.Role
import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.validation.Valid

@Entity
class Layout(@Valid var role: Role, @ManyToOne var widget: Widget) : Identifiable()