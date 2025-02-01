package dev.rohenkohl.bloggin.component.domain.annotation.interceptor

import dev.rohenkohl.bloggin.person.domain.service.LoginService
import dev.rohenkohl.bloggin.person.domain.service.PermissionService
import dev.rohenkohl.bloggin.component.domain.annotation.Owning
import dev.rohenkohl.bloggin.component.domain.model.Layout
import dev.rohenkohl.bloggin.component.domain.model.Ownership
import dev.rohenkohl.bloggin.component.domain.repository.OwnershipRepository
import dev.rohenkohl.bloggin.component.domain.repository.WidgetRepository
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.extension.pipe
import io.quarkus.security.Authenticated
import io.quarkus.security.AuthenticationFailedException
import jakarta.inject.Inject
import jakarta.interceptor.AroundInvoke
import jakarta.interceptor.Interceptor
import jakarta.interceptor.InvocationContext
import jakarta.persistence.NoResultException
import jakarta.ws.rs.InternalServerErrorException

@Owning
@Authenticated
@Interceptor
internal class OwningInterceptor {

    @Inject
    private lateinit var ownershipRepository: OwnershipRepository

    @Inject
    private lateinit var widgetRepository: WidgetRepository

    @Inject
    private lateinit var permissionService: PermissionService

    @Inject
    private lateinit var loginService: LoginService

    @AroundInvoke
    fun enforceOwnership(invocationContext: InvocationContext): Any {
        try {
            val person = loginService.find().payload.person ?: throw AuthenticationFailedException()
            val role = permissionService.find(Reference(person.uuid)).payload.role

            return if (readLayout(invocationContext).role matches role) invocationContext.proceed() else throw AuthenticationFailedException()
        } catch (noResultException: NoResultException) {
            throw AuthenticationFailedException(noResultException)
        }
    }

    private fun readLayout(invocationContext: InvocationContext): Layout {
        val reference = invocationContext.parameters.first()

        if (reference is Reference<*>) widgetRepository.readByUUID(reference.uuid) pipe ownershipRepository::readByWidget pipe Ownership::layout

        throw InternalServerErrorException()
    }
}