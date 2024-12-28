package de.ottorohenkohl.bloggin.zero.extension

import de.ottorohenkohl.bloggin.zero.domain.model.value.Description
import de.ottorohenkohl.bloggin.zero.domain.model.value.Name
import jakarta.ws.rs.BadRequestException

fun String?.nonnullDescription(): Description = if (this != null) Description(this) else throw BadRequestException("Description cannot be null")
fun String?.nullableDescription(): Description? = if (this != null) Description(this) else null

fun String?.nonnullName(): Name = if (this != null) Name(this) else throw BadRequestException("Name cannot be null")
fun String?.nullableName(): Name? = if (this != null) Name(this) else null
