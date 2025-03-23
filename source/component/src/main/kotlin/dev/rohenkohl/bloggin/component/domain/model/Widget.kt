package dev.rohenkohl.bloggin.component.domain.model

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType

@Entity
abstract class Widget : Identifiable()