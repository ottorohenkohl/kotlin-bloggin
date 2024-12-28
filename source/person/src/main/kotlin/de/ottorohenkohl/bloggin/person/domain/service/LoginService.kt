package de.ottorohenkohl.bloggin.person.domain.service

import de.ottorohenkohl.bloggin.person.domain.service.mapper.LoginMapper
import de.ottorohenkohl.bloggin.person.domain.service.transfer.LoginDTO
import de.ottorohenkohl.bloggin.person.domain.repository.LoginRepository
import de.ottorohenkohl.bloggin.person.extension.nonnullIssuer
import de.ottorohenkohl.bloggin.person.extension.nonnullSubject
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.pipe
import io.quarkus.oidc.IdToken
import io.quarkus.security.AuthenticationFailedException
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.control.ActivateRequestContext
import jakarta.inject.Inject
import jakarta.persistence.Access
import jakarta.transaction.Transactional
import jakarta.ws.rs.BadRequestException
import org.eclipse.microprofile.jwt.JsonWebToken

@ActivateRequestContext
@ApplicationScoped
class LoginService {

    @IdToken
    @Inject
    private lateinit var jsonWebToken: JsonWebToken

    @Inject
    private lateinit var loginMapper: LoginMapper

    @Inject
    private lateinit var loginRepository: LoginRepository

    @Transactional
    fun find(): Content<LoginDTO> {
        try {
            val issuer = jsonWebToken.issuer.nonnullIssuer()
            val subject = jsonWebToken.subject.nonnullSubject()

            return loginRepository.readByIssuerSubject(issuer, subject) pipe loginMapper::export
        } catch (badRequestException: BadRequestException) {
            throw AuthenticationFailedException(badRequestException)
        }
    }
}