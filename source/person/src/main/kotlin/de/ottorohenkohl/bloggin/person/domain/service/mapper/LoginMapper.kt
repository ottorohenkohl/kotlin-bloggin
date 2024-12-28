package de.ottorohenkohl.bloggin.person.domain.service.mapper

import de.ottorohenkohl.bloggin.person.domain.service.transfer.LoginDTO
import de.ottorohenkohl.bloggin.person.domain.model.Login
import de.ottorohenkohl.bloggin.person.domain.repository.LoginRepository
import de.ottorohenkohl.bloggin.person.extension.nonnullIssuer
import de.ottorohenkohl.bloggin.person.extension.nonnullSubject
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Exporter
import de.ottorohenkohl.bloggin.zero.domain.service.mapper.Importer
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import de.ottorohenkohl.bloggin.zero.extension.nonnull
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