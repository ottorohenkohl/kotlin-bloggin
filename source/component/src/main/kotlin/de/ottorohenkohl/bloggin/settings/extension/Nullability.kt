package de.ottorohenkohl.bloggin.settings.extension

import de.ottorohenkohl.bloggin.settings.domain.model.value.Data
import de.ottorohenkohl.bloggin.settings.domain.model.value.Mimetype
import de.ottorohenkohl.bloggin.settings.domain.model.value.Text
import jakarta.ws.rs.BadRequestException

fun List<Byte>?.nonnullData(): Data = if (this != null) Data(this) else throw BadRequestException("Data cannot be null")
fun List<Byte>?.nullableData(): Data? = if (this != null) Data(this) else null

fun String?.nonnullMimetype(): Mimetype = if (this != null) Mimetype(this) else throw BadRequestException("Mimetype cannot be null")
fun String?.nullableMimetype(): Mimetype? = if (this != null) Mimetype(this) else null

fun String?.nonnullText(): Text = if (this != null) Text(this) else throw BadRequestException("Text cannot be null")
fun String?.nullableText(): Text? = if (this != null) Text(this) else null