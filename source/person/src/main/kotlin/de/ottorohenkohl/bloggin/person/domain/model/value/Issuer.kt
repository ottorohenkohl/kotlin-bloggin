package de.ottorohenkohl.bloggin.person.domain.model.value

import jakarta.persistence.Embeddable
import org.hibernate.validator.constraints.URL

@Embeddable
data class Issuer(@URL val value: String)