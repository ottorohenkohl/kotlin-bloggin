package de.ottorohenkohl.bloggin.settings.extension

import jakarta.ws.rs.core.HttpHeaders

fun HttpHeaders.contentType(): String = getHeaderString("Content-Type")