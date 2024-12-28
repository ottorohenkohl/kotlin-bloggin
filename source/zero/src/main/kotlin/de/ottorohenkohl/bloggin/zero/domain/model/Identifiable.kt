package de.ottorohenkohl.bloggin.zero.domain.model

import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.MappedSuperclass
import jakarta.validation.Valid
import java.util.*

@MappedSuperclass
abstract class Identifiable {

    @Id
    open var uuid: UUID = UUID.randomUUID()
}