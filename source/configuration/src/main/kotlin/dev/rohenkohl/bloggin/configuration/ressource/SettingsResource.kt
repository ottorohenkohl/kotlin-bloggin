package dev.rohenkohl.bloggin.configuration.ressource

import dev.rohenkohl.bloggin.configuration.domain.service.ConfigurationService
import dev.rohenkohl.bloggin.configuration.domain.service.transfer.ConfigurationDTO
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.extension.path
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.UriInfo
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestResponse
import java.util.*

@Path("/settings")
internal class SettingsResource(val configurationService: ConfigurationService) {

    @Path("/")
    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(): RestResponse<ConfigurationDTO> {
        return configurationService.find() pipe { RestResponse.ok(it.payload) }
    }

    @Path("/{uuid}")
    @PATCH
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@RestPath uuid: UUID, @Context uriInfo: UriInfo, configurationDTO: ConfigurationDTO): RestResponse<ConfigurationDTO> {
        return configurationService.change(Reference(configurationDTO, uuid)) pipe { RestResponse.seeOther(uriInfo.path(it, SettingsResource::class)) }
    }
}