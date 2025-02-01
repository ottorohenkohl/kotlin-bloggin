package dev.rohenkohl.bloggin.person.ressource

import dev.rohenkohl.bloggin.person.domain.service.LoginService
import dev.rohenkohl.bloggin.person.domain.service.transfer.LoginDTO
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import org.jboss.resteasy.reactive.RestResponse

@Path("/logins")
internal class LoginResource(val loginService: LoginService) {

    @Path("/")
    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(): RestResponse<LoginDTO> {
        return loginService.find() pipe { RestResponse.ok(it.payload) }
    }
}