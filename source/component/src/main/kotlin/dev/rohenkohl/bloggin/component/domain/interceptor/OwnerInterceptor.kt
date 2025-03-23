package dev.rohenkohl.bloggin.component.domain.interceptor

import dev.rohenkohl.bloggin.user.domain.service.LoginService
import dev.rohenkohl.bloggin.user.domain.service.PermissionService
import dev.rohenkohl.bloggin.component.domain.interceptor.binding.Owner
import dev.rohenkohl.bloggin.component.domain.service.LayoutService
import dev.rohenkohl.bloggin.zero.domain.interceptor.PermittedInterceptor
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import io.quarkus.security.AuthenticationFailedException
import jakarta.interceptor.AroundInvoke
import jakarta.interceptor.Interceptor
import jakarta.interceptor.InvocationContext
import java.util.UUID

@Owner
@Interceptor
internal class OwnerInterceptor(val layoutService: LayoutService, val loginService: LoginService, val permissionService: PermissionService) : PermittedInterceptor() {

    @AroundInvoke
    override fun aroundInvoke(invocationContext: InvocationContext) = intercept(invocationContext)

    override fun throwingGuard(invocationContext: InvocationContext, required: Role) {
        val uuid = invocationContext.parameters.first() as UUID

        val person = loginService.find().payload.person ?: throw AuthenticationFailedException()
        val role = permissionService.find(Reference(person.uuid)).payload.role ?: throw AuthenticationFailedException()

        if (role < required && role != layoutService.find(Reference(uuid)).payload.role) throw AuthenticationFailedException()
    }
}