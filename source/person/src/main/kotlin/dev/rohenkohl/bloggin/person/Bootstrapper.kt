package dev.rohenkohl.bloggin.person

import dev.rohenkohl.bloggin.person.domain.annotation.value.Role
import dev.rohenkohl.bloggin.person.domain.service.LoginService
import dev.rohenkohl.bloggin.person.domain.service.PermissionService
import dev.rohenkohl.bloggin.person.domain.service.PersonService
import dev.rohenkohl.bloggin.person.domain.service.transfer.LoginDTO
import dev.rohenkohl.bloggin.person.domain.service.transfer.PersonDTO
import dev.rohenkohl.bloggin.person.extension.LoginExistsException
import dev.rohenkohl.bloggin.person.extension.PersonExistsException
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.extension.pipe
import io.quarkus.logging.Log
import io.quarkus.runtime.Startup
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.NoResultException
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.swing.text.AbstractDocument.Content

@ApplicationScoped
internal class Bootstrapper(val loginService: LoginService, val personService: PersonService) {

    @Inject
    private lateinit var permissionService: PermissionService

    @ConfigProperty(name = "bloggin.person.admin.issuer", defaultValue = " ")
    protected lateinit var issuer: String

    @ConfigProperty(name = "bloggin.person.admin.nickname", defaultValue = " ")
    protected lateinit var nickname: String

    @ConfigProperty(name = "bloggin.person.admin.subject", defaultValue = " ")
    protected lateinit var subject: String

    @ConfigProperty(name = "bloggin.person.admin.username", defaultValue = " ")
    protected lateinit var username: String

    @Startup
    fun bootstrap() {
        if (issuer.isBlank()) return Log.info("Skipping user creation; issuer is blank")
        if (nickname.isBlank()) return Log.info("Skipping user creation; nickname is blank")
        if (subject.isBlank()) return Log.info("Skipping user creation; subject is blank")
        if (username.isBlank()) return Log.info("Skipping user creation; username is blank")

        Log.info("Registering user '$username' with issuer '$issuer' and subject '$subject'")

        try {
            val person = personService.store(PersonDTO(nickname, username))
            val permission = permissionService.find(Reference(person.uuid))

            loginService.store(LoginDTO(issuer, Reference(person.uuid), subject))
            permissionService.change(Reference(permission.payload.copy(role = Role.ADMIN), permission.uuid))
        } catch (personExistsException: PersonExistsException) {
            Log.info("Skipping user creation; username is already taken")
        } catch (loginExistsException: LoginExistsException) {
            Log.info("Skipping user creation; subject and issuer combination is already taken")
        }
    }
}