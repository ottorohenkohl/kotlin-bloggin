package dev.rohenkohl.bloggin.person.ressource

import dev.rohenkohl.bloggin.person.domain.annotation.Minimum
import dev.rohenkohl.bloggin.person.domain.annotation.value.Role.ADMIN
import dev.rohenkohl.bloggin.person.domain.annotation.value.Role.AUTHOR
import dev.rohenkohl.bloggin.person.domain.service.PersonService
import dev.rohenkohl.bloggin.person.domain.service.transfer.PersonDTO
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Range
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Range.Excerpt
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
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

@Path("/persons")
internal class PersonResource(val personService: PersonService) {

    @Path("/{uuid}")
    @DELETE
    @Minimum(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun delete(@RestPath uuid: UUID): RestResponse<Unit> {
        return personService.erase(Reference(uuid)) pipe { RestResponse.accepted() }
    }

    @Path("/")
    @GET
    @Minimum(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(@RestQuery number: Int, @RestQuery size: Int): RestResponse<Excerpt<PersonDTO>> {
        return personService.find(Range(number, size)) pipe { RestResponse.ok(it) }
    }

    @Path("/{uuid}")
    @GET
    @Minimum(AUTHOR)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(@RestPath uuid: UUID): RestResponse<PersonDTO> {
        return personService.find(Reference(uuid)) pipe { RestResponse.ok(it.payload) }
    }

    @Path("/{uuid}")
    @PATCH
    @Minimum(AUTHOR)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@RestPath uuid: UUID, @Context uriInfo: UriInfo, personDTO: PersonDTO): RestResponse<PersonDTO> {
        return personService.change(Reference(personDTO, uuid)) pipe { RestResponse.seeOther(uriInfo.path(it)) }
    }

    @Path("/")
    @POST
    @Minimum(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun post(@Context uriInfo: UriInfo, personDTO: PersonDTO): RestResponse<PersonDTO> {
        return personService.store(personDTO) pipe { RestResponse.seeOther(uriInfo.path(it)) }
    }
}