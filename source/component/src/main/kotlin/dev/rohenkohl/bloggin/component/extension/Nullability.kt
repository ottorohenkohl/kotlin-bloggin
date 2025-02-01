package dev.rohenkohl.bloggin.component.extension

import dev.rohenkohl.bloggin.component.domain.model.value.Data
import dev.rohenkohl.bloggin.component.domain.model.value.Mimetype
import dev.rohenkohl.bloggin.component.domain.model.value.Text
import jakarta.ws.rs.BadRequestException

fun List<Byte>?.nonnullData(): Data = if (this != null) Data(this) else throw BadRequestException("Data cannot be null")
fun List<Byte>?.nullableData(): Data? = if (this != null) Data(this) else null

fun String?.nonnullMimetype(): Mimetype =
    if (this != null) Mimetype(this) else throw BadRequestException("Mimetype cannot be null")

fun String?.nullableMimetype(): Mimetype? = if (this != null) Mimetype(this) else null

fun String?.nonnullText(): Text = if (this != null) Text(this) else throw BadRequestException("Text cannot be null")
fun String?.nullableText(): Text? = if (this != null) Text(this) else null