package dev.rohenkohl.bloggin.person.domain.annotation

import dev.rohenkohl.bloggin.person.domain.annotation.value.Role
import io.quarkus.security.Authenticated
import jakarta.enterprise.util.Nonbinding
import jakarta.interceptor.InterceptorBinding


@Authenticated
@InterceptorBinding
annotation class Minimum(@get:Nonbinding val value: Role)