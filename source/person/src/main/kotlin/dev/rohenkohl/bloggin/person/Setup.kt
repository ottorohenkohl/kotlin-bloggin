package dev.rohenkohl.bloggin.person

import dev.rohenkohl.bloggin.configuration.domain.service.EnvironmentService
import dev.rohenkohl.bloggin.person.domain.annotation.value.Role
import dev.rohenkohl.bloggin.person.domain.service.LoginService
import dev.rohenkohl.bloggin.person.domain.service.PermissionService
import dev.rohenkohl.bloggin.person.domain.service.PersonService
import dev.rohenkohl.bloggin.person.domain.service.transfer.LoginDTO
import dev.rohenkohl.bloggin.person.domain.service.transfer.PersonDTO
import dev.rohenkohl.bloggin.person.extension.LoginExistsException
import dev.rohenkohl.bloggin.person.extension.PersonExistsException
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import io.quarkus.logging.Log
import io.quarkus.runtime.Startup
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.BadRequestException

@ApplicationScoped
internal class Setup(val loginService: LoginService, val permissionService: PermissionService, val personService: PersonService, environmentService: EnvironmentService) {

    private val issuer: String = environmentService.string("person.setup.admin.issuer", "")
    private val nickname: String = environmentService.string("person.setup.admin.nickname", "")
    private val subject: String = environmentService.string("person.setup.admin.subject", "")
    private val username: String = environmentService.string("person.setup.admin.username", "")

    @Startup
    fun bootstrap() {
        try {
            val person = personService.store(PersonDTO(nickname, username))
            val permission = permissionService.find(Reference(person.uuid))

            loginService.store(LoginDTO(issuer, Reference(person.uuid), subject))
            permissionService.change(Reference(permission.payload.copy(role = Role.ADMIN), permission.uuid))
        } catch (personExistsException: PersonExistsException) {
            Log.info("Skipping user creation; username is already taken")
        } catch (loginExistsException: LoginExistsException) {
            Log.info("Skipping user creation; subject and issuer combination is already taken")
        } catch (badRequestException: BadRequestException) {
            Log.error("User creation failed; user data is invalid")
        }
    }
}