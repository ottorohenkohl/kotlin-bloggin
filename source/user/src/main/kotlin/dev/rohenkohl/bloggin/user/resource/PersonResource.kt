package dev.rohenkohl.bloggin.user.resource

import dev.rohenkohl.bloggin.zero.domain.model.constant.Role.ADMIN
import dev.rohenkohl.bloggin.user.domain.service.PersonService
import dev.rohenkohl.bloggin.user.domain.interceptor.binding.Itself
import dev.rohenkohl.bloggin.user.domain.service.transfer.LoginTransfer
import dev.rohenkohl.bloggin.user.domain.service.transfer.PersonTransfer
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Minimum
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Permitted
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Range
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Range.Excerpt
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.path
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.UriInfo
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestQuery
import org.jboss.resteasy.reactive.RestResponse
import java.util.*

@Path("/user/persons")
class PersonResource internal constructor(val personService: PersonService) {

    @Path("/{uuid}")
    @DELETE
    @Minimum
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun delete(@RestPath uuid: UUID): RestResponse<Unit> =
        personService.erase(Reference(uuid)) pipe { RestResponse.accepted() }

    @Path("/")
    @GET
    @Minimum
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(@RestQuery number: Int, @RestQuery size: Int): RestResponse<Excerpt<PersonTransfer>> =
        personService.find(Range(number, size)) pipe { RestResponse.ok(it) }

    @Path("/{uuid}")
    @GET
    @Itself
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(@RestPath uuid: UUID): RestResponse<PersonTransfer> =
        personService.find(Reference(uuid)) pipe { RestResponse.ok(it.payload) }

    @Path("/{uuid}")
    @PATCH
    @Itself
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@RestPath uuid: UUID, @Context uriInfo: UriInfo, personTransfer: PersonTransfer): RestResponse<PersonTransfer> =
        personService.change(Content(personTransfer, uuid)) pipe { RestResponse.seeOther(uriInfo.requestUri) }

    @Path("/")
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun post(@Context uriInfo: UriInfo, personTransfer: PersonTransfer): RestResponse<LoginTransfer> =
        personService.store(personTransfer) pipe { RestResponse.seeOther(uriInfo.path(it)) }
}