package dev.rohenkohl.bloggin.user.resource

import dev.rohenkohl.bloggin.user.domain.service.LoginService
import dev.rohenkohl.bloggin.user.domain.service.transfer.LoginTransfer
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import org.jboss.resteasy.reactive.RestResponse

@Path("/user/logins")
internal class LoginResource(val loginService: LoginService) {

    @Path("/")
    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(): RestResponse<LoginTransfer> =
        loginService.find() pipe { RestResponse.ok(it.payload) }
}