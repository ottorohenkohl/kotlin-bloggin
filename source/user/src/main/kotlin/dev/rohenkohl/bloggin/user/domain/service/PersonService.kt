package dev.rohenkohl.bloggin.user.domain.service

import dev.rohenkohl.bloggin.user.domain.model.Person
import dev.rohenkohl.bloggin.user.domain.model.repository.PersonRepository
import dev.rohenkohl.bloggin.user.domain.service.mapper.PersonMapper
import dev.rohenkohl.bloggin.user.domain.service.transfer.LoginTransfer
import dev.rohenkohl.bloggin.user.domain.service.transfer.PermissionTransfer
import dev.rohenkohl.bloggin.user.domain.service.transfer.PersonTransfer
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Range
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Range.Excerpt
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.*
import io.quarkus.oidc.OidcConfigurationMetadata
import io.quarkus.oidc.UserInfo
import io.quarkus.security.AuthenticationFailedException
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.BadRequestException
import org.hibernate.query.Page

@ApplicationScoped
class PersonService(val loginService: LoginService, val oidcConfigurationMetadata: OidcConfigurationMetadata, val permissionService: PermissionService, val personMapper: PersonMapper, val userInfo: UserInfo) {

    private lateinit var personRepository: PersonRepository

    @Inject
    internal constructor(loginService: LoginService, oidcConfigurationMetadata: OidcConfigurationMetadata, personRepository: PersonRepository, permissionService: PermissionService, personMapper: PersonMapper, userInfo: UserInfo) : this(loginService, oidcConfigurationMetadata, permissionService, personMapper, userInfo) {
        this.personRepository = personRepository
    }

    @Transactional
    fun change(content: Content<PersonTransfer>): Reference<Person> {
        return personMapper.modify(content)
    }

    @Transactional
    fun erase(reference: Reference<Person>): Reference<Person> {
        return personMapper.load(reference) pipe personRepository::delete yield reference
    }

    @Transactional
    fun find(range: Range<PersonTransfer>): Excerpt<PersonTransfer> {
        return personRepository.readByPage(range.page()).map(personMapper::export) pipe range::fill
    }

    @Transactional
    fun find(reference: Reference<Person>): Content<PersonTransfer> {
        return personMapper.load(reference) pipe personMapper::export
    }

    @Transactional
    fun store(personTransfer: PersonTransfer): Reference<Person> {
        val issuer = rethrow<BadRequestException, _>(oidcConfigurationMetadata.issuer::nonnull) { AuthenticationFailedException(it) }
        val subject = rethrow<BadRequestException, _>(userInfo.subject::nonnull) { AuthenticationFailedException(it) }

        val first = personRepository.readByPage(Page.first(1)).isEmpty()
        val person = personMapper.import(personTransfer)

        val role = if (first) Role.ADMIN else Role.GUEST

        person pipe { Reference(it) } pipe { LoginTransfer(issuer, subject, it) } pipe loginService::store
        person pipe { Reference(it) } pipe { PermissionTransfer(role, it) } pipe permissionService::store

        return Reference(person)
    }
}