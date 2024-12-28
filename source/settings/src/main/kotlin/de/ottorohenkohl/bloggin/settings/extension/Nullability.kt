package de.ottorohenkohl.bloggin.settings.extension

import de.ottorohenkohl.bloggin.settings.domain.model.value.Title
import jakarta.ws.rs.BadRequestException

fun String?.nonnullTitle(): Title  = if (this != null) Title(this) else throw BadRequestException("Title cannot be null")
fun String?.nullableTitle(): Title? = if (this != null) Title(this) else null