package dev.rohenkohl.bloggin.zero.domain.interceptor.binding

import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import io.quarkus.security.Authenticated

@Authenticated
annotation class Permitted(val value: Role)