package dev.rohenkohl.bloggin.zero.domain.interceptor.binding

import io.quarkus.security.Authenticated
import jakarta.interceptor.InterceptorBinding


@Authenticated
@InterceptorBinding
annotation class Minimum