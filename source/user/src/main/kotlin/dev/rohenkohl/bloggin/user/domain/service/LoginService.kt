package dev.rohenkohl.bloggin.user.domain.service

import dev.rohenkohl.bloggin.user.domain.model.Login
import dev.rohenkohl.bloggin.user.domain.model.repository.LoginRepository
import dev.rohenkohl.bloggin.user.domain.exception.LoginExistsException
import dev.rohenkohl.bloggin.user.domain.service.mapper.LoginMapper
import dev.rohenkohl.bloggin.user.domain.service.transfer.LoginTransfer
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.*
import io.quarkus.oidc.OidcConfigurationMetadata
import io.quarkus.oidc.UserInfo
import io.quarkus.security.AuthenticationFailedException
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.NoResultException
import jakarta.transaction.Transactional
import jakarta.ws.rs.BadRequestException

@ApplicationScoped
class LoginService(val loginMapper: LoginMapper, val oidcConfigurationMetadata: OidcConfigurationMetadata, val userInfo: UserInfo) {

    private lateinit var loginRepository: LoginRepository

    @Inject
    internal constructor(loginRepository: LoginRepository, loginMapper: LoginMapper, oidcConfigurationMetadata: OidcConfigurationMetadata, userInfo: UserInfo) : this(loginMapper, oidcConfigurationMetadata, userInfo) {
        this.loginRepository = loginRepository
    }

    @Transactional
    fun find(): Content<LoginTransfer> {
        val issuer = rethrow<BadRequestException, _>(oidcConfigurationMetadata.issuer::nonnull) { AuthenticationFailedException(it) }
        val subject = rethrow<BadRequestException, _>(userInfo.subject::nonnull) { AuthenticationFailedException(it) }


        return rethrow<NoResultException, _>({ loginRepository.readByIssuerSubject(issuer, subject) }, { AuthenticationFailedException(it) }) pipe loginMapper::export
    }

    @Transactional
    fun store(loginTransfer: LoginTransfer): Reference<Login> {
        val issuer = loginTransfer.issuer.nonnull()
        val subject = loginTransfer.subject.nonnull()

        return fail<NoResultException, _>({ loginRepository.readByIssuerSubject(issuer, subject) }, LoginExistsException()) yield loginMapper.import(loginTransfer) pipe { Reference(it) }
    }
}