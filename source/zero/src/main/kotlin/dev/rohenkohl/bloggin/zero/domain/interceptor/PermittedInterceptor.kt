package dev.rohenkohl.bloggin.zero.domain.interceptor

import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Permitted
import dev.rohenkohl.bloggin.zero.extension.rethrow
import io.quarkus.security.AuthenticationFailedException
import jakarta.interceptor.InvocationContext

abstract class PermittedInterceptor {

    abstract fun throwingGuard(invocationContext: InvocationContext, required: Role): Any
    abstract fun aroundInvoke(invocationContext: InvocationContext): Any

    fun intercept(invocationContext: InvocationContext): Any {
        rethrow<Throwable, _>({ throwingGuard(invocationContext, invocationContext.method.getAnnotation(Permitted::class.java).value) }, { AuthenticationFailedException(it) })

        return invocationContext.proceed()
    }
}