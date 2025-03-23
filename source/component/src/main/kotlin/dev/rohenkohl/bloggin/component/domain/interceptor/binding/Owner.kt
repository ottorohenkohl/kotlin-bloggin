package dev.rohenkohl.bloggin.component.domain.interceptor.binding

import io.quarkus.security.Authenticated
import jakarta.interceptor.InterceptorBinding


@Authenticated
@InterceptorBinding
annotation class Owner