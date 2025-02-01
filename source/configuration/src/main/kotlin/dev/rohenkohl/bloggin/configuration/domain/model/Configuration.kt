package dev.rohenkohl.bloggin.configuration.domain.model

import dev.rohenkohl.bloggin.configuration.domain.model.value.Title
import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import dev.rohenkohl.bloggin.zero.domain.model.value.Description
import jakarta.persistence.Entity
import jakarta.validation.Valid

@Entity
class Configuration(@Valid var description: Description, @Valid var title: Title) : Identifiable()