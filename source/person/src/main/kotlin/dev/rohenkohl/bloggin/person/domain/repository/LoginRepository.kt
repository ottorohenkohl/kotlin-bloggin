package dev.rohenkohl.bloggin.person.domain.repository

import dev.rohenkohl.bloggin.person.domain.model.Login
import dev.rohenkohl.bloggin.person.domain.model.value.Issuer
import dev.rohenkohl.bloggin.person.domain.model.value.Subject
import dev.rohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface LoginRepository : Repository<Login> {

    @Find
    fun readByIssuerSubject(issuer: Issuer, subject: Subject): Login

    @Find
    override fun readByUUID(uuid: UUID): Login
}
