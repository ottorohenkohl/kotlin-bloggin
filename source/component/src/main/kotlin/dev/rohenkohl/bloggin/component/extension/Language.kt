package dev.rohenkohl.bloggin.component.extension

import jakarta.ws.rs.core.HttpHeaders

fun HttpHeaders.contentType(): String = getHeaderString("Content-Type")