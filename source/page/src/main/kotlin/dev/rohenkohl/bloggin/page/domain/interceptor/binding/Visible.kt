package dev.rohenkohl.bloggin.page.domain.interceptor.binding

import io.quarkus.security.Authenticated
import jakarta.interceptor.InterceptorBinding


@Authenticated
@InterceptorBinding
annotation class Visible