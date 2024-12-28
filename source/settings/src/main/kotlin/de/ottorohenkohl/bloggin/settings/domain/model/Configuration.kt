package de.ottorohenkohl.bloggin.settings.domain.model

import de.ottorohenkohl.bloggin.settings.domain.model.value.Title
import de.ottorohenkohl.bloggin.zero.domain.model.Identifiable
import de.ottorohenkohl.bloggin.zero.domain.model.value.Description
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Configuration(@Valid var description: Description, @Valid var title: Title) : Identifiable()