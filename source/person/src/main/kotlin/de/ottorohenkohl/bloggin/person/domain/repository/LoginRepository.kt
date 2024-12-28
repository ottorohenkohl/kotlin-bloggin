package de.ottorohenkohl.bloggin.person.domain.repository

import de.ottorohenkohl.bloggin.person.domain.model.Login
import de.ottorohenkohl.bloggin.person.domain.model.value.Issuer
import de.ottorohenkohl.bloggin.person.domain.model.value.Subject
import de.ottorohenkohl.bloggin.zero.domain.repository.Repository
import org.hibernate.annotations.processing.Find
import java.util.*

internal interface LoginRepository : Repository<Login> {

    @Find
    fun readByIssuerSubject(issuer: Issuer, subject: Subject): Login

    @Find
    override fun readByUUID(uuid: UUID): Login
}
