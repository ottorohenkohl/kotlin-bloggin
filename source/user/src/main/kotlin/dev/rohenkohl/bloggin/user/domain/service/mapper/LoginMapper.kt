package dev.rohenkohl.bloggin.user.domain.service.mapper

import dev.rohenkohl.bloggin.user.domain.model.Login
import dev.rohenkohl.bloggin.user.domain.model.repository.LoginRepository
import dev.rohenkohl.bloggin.user.domain.service.transfer.LoginTransfer
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Exporter
import dev.rohenkohl.bloggin.zero.domain.service.mapper.Importer
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.nonnull
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class LoginMapper(val personMapper: PersonMapper) : Exporter<LoginTransfer, Login>, Importer<LoginTransfer, Login> {

    private lateinit var loginRepository: LoginRepository

    @Inject
    internal constructor(loginRepository: LoginRepository, personMapper: PersonMapper) : this(personMapper) {
        this.loginRepository = loginRepository
    }

    override fun export(identifiable: Login): Content<LoginTransfer> {
        val personReference = Reference(identifiable.person)
        val loginTransfer = LoginTransfer(identifiable.issuer, identifiable.subject, personReference)

        return Content(loginTransfer, identifiable.uuid)
    }

    override fun import(transfer: LoginTransfer): Login {
        val person = personMapper.load(transfer.person.nonnull())

        return Login(transfer.issuer.nonnull(), transfer.subject.nonnull(), person) pipe loginRepository::create
    }
}