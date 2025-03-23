package dev.rohenkohl.bloggin.user.domain.model.repository

import dev.rohenkohl.bloggin.user.domain.model.Login
import dev.rohenkohl.bloggin.zero.domain.model.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface LoginRepository : Repository<Login> {

    @Find
    fun readByIssuerSubject(issuer: String, subject: String): Login

    @Find
    fun readByUUID(uuid: UUID): Login
}
