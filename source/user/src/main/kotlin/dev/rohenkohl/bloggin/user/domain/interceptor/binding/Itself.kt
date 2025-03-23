package dev.rohenkohl.bloggin.user.domain.interceptor.binding

import io.quarkus.security.Authenticated
import jakarta.interceptor.InterceptorBinding


@Authenticated
@InterceptorBinding
annotation class Itself