package dev.rohenkohl.bloggin.page.domain.interceptor

import dev.rohenkohl.bloggin.page.domain.interceptor.binding.Managing
import dev.rohenkohl.bloggin.page.domain.service.SiteService
import dev.rohenkohl.bloggin.user.domain.service.LoginService
import dev.rohenkohl.bloggin.user.domain.service.PermissionService
import dev.rohenkohl.bloggin.zero.domain.interceptor.PermittedInterceptor
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import io.quarkus.security.AuthenticationFailedException
import jakarta.interceptor.AroundInvoke
import jakarta.interceptor.Interceptor
import jakarta.interceptor.InvocationContext
import java.util.*

@Managing
@Interceptor
internal class VisibleInterceptor(val loginService: LoginService, val permissionService: PermissionService, val siteService: SiteService) : PermittedInterceptor() {

    @AroundInvoke
    override fun aroundInvoke(invocationContext: InvocationContext) = intercept(invocationContext)

    override fun throwingGuard(invocationContext: InvocationContext, required: Role) {
        val uuid = invocationContext.parameters.first() as UUID

        val person = loginService.find().payload.person ?: throw AuthenticationFailedException()
        val role = permissionService.find(Reference(person.uuid)).payload.role ?: throw AuthenticationFailedException()
        val visible = siteService.find(Reference(uuid)).payload.visible ?: throw AuthenticationFailedException()

        if (role < required && !visible) throw AuthenticationFailedException()
    }
}