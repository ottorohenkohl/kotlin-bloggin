package dev.rohenkohl.bloggin.zero.domain.model

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.*

@MappedSuperclass
abstract class Identifiable {

    @Id
    open var uuid: UUID = UUID.randomUUID()
}