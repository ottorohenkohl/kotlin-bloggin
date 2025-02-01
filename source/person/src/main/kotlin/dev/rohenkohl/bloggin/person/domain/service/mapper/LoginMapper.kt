package dev.rohenkohl.bloggin.person.domain.service.mapper

import dev.rohenkohl.bloggin.person.domain.model.Login
import dev.rohenkohl.bloggin.person.domain.repository.LoginRepository
import dev.rohenkohl.bloggin.person.domain.service.transfer.LoginDTO
import dev.rohenkohl.bloggin.person.extension.nonnullIssuer
import dev.rohenkohl.bloggin.person.extension.nonnullSubject
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class LoginMapper : Exporter<LoginDTO, Login>, Importer<LoginDTO, Login> {

    @Inject
    private lateinit var loginRepository: LoginRepository

    @Inject
    private lateinit var personMapper: PersonMapper

    override fun export(identifiable: Login): Content<LoginDTO> {
        val personDTO = Reference(identifiable.person)
        val loginDTO = LoginDTO(identifiable.issuer.value, personDTO, identifiable.subject.value)

        return Reference(loginDTO, identifiable.uuid)
    }

    override fun import(dto: LoginDTO): Login {
        val login = Login(dto.issuer.nonnullIssuer(), personMapper.load(dto.person.nonnull()), dto.subject.nonnullSubject())

        return loginRepository.create(login)
    }
}