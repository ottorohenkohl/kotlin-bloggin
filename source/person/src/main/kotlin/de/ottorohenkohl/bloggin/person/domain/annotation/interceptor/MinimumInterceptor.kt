package de.ottorohenkohl.bloggin.person.domain.annotation.interceptor

import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.person.domain.annotation.Minimum
import de.ottorohenkohl.bloggin.person.domain.annotation.value.Role
import de.ottorohenkohl.bloggin.person.domain.annotation.value.Role.ADMIN
import de.ottorohenkohl.bloggin.person.domain.service.LoginService
import de.ottorohenkohl.bloggin.person.domain.service.PermissionService
import io.quarkus.security.Authenticated
import io.quarkus.security.AuthenticationFailedException
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.Dependent
import jakarta.inject.Inject
import jakarta.interceptor.AroundInvoke
import jakarta.interceptor.Interceptor
import jakarta.interceptor.InvocationContext
import jakarta.persistence.NoResultException

@Minimum(ADMIN)
@Authenticated
@Interceptor
internal class MinimumInterceptor {

    @Inject
    private lateinit var loginService: LoginService

    @Inject
    private lateinit var permissionService: PermissionService

    @AroundInvoke
    fun enforceRole(invocationContext: InvocationContext): Any {
        try {
            val person = loginService.find().payload.person ?: throw AuthenticationFailedException()
            val permission = permissionService.find(Reference(person.uuid))

            return if (readRole(invocationContext) matches permission.payload.role) invocationContext.proceed() else throw AuthenticationFailedException()
        } catch (noResultException: NoResultException) {
            throw AuthenticationFailedException(noResultException)
        }
    }

    private fun readRole(invocationContext: InvocationContext): Role {
        val annotation = invocationContext.method.getAnnotation(Minimum::class.java)

        return annotation?.value ?: invocationContext.target.javaClass.getAnnotation(Minimum::class.java).value
    }
}