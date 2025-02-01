package dev.rohenkohl.bloggin.person.domain.service

import dev.rohenkohl.bloggin.person.domain.model.value.Issuer
import dev.rohenkohl.bloggin.person.domain.repository.LoginRepository
import dev.rohenkohl.bloggin.person.domain.service.mapper.LoginMapper
import dev.rohenkohl.bloggin.person.domain.service.transfer.LoginDTO
import dev.rohenkohl.bloggin.person.extension.LoginExistsException
import dev.rohenkohl.bloggin.person.extension.nonnullIssuer
import dev.rohenkohl.bloggin.person.extension.nonnullSubject
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.fail
import dev.rohenkohl.bloggin.zero.extension.rethrow
import dev.rohenkohl.bloggin.zero.extension.pipe
import dev.rohenkohl.bloggin.zero.extension.yield
import io.quarkus.oidc.OidcConfigurationMetadata
import io.quarkus.oidc.UserInfo
import io.quarkus.security.AuthenticationFailedException
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.control.ActivateRequestContext
import jakarta.inject.Inject
import jakarta.persistence.NoResultException
import jakarta.transaction.Transactional
import jakarta.ws.rs.BadRequestException

@ActivateRequestContext
@ApplicationScoped
class LoginService {

    @Inject
    private lateinit var loginMapper: LoginMapper

    @Inject
    private lateinit var loginRepository: LoginRepository

    @Inject
    private lateinit var oidcConfigurationMetadata: OidcConfigurationMetadata

    @Inject
    private lateinit var userInfo: UserInfo

    @Transactional
    fun find(): Content<LoginDTO> {
        val issuer = rethrow<BadRequestException, _>(oidcConfigurationMetadata.issuer::nonnullIssuer, AuthenticationFailedException())
        val subject = rethrow<BadRequestException, _>(userInfo.subject::nonnullSubject, AuthenticationFailedException())

        return rethrow<NoResultException, _>({ loginRepository.readByIssuerSubject(issuer, subject) }, AuthenticationFailedException())  pipe loginMapper::export
    }

    @Transactional
    fun store(loginDTO: LoginDTO): Reference<LoginDTO> {
        val issuer = loginDTO.issuer.nonnullIssuer()
        val subject = loginDTO.subject.nonnullSubject()

        return fail<NoResultException>({ loginRepository.readByIssuerSubject(issuer, subject) }, LoginExistsException())  yield loginMapper.import(loginDTO) pipe loginMapper::export
    }
}