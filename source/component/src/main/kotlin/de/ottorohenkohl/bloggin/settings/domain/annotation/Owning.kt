package de.ottorohenkohl.bloggin.settings.domain.annotation

import io.quarkus.security.Authenticated
import jakarta.interceptor.InterceptorBinding


@Authenticated
@InterceptorBinding
annotation class Owning