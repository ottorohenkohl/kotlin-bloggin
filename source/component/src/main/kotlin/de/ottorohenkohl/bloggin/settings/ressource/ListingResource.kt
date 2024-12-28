package de.ottorohenkohl.bloggin.settings.ressource

import de.ottorohenkohl.bloggin.settings.domain.service.ListingService
import de.ottorohenkohl.bloggin.settings.domain.service.transfer.WidgetDTO
import de.ottorohenkohl.bloggin.settings.domain.service.transfer.ImageDTO
import de.ottorohenkohl.bloggin.settings.extension.contentType
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.extension.path
import de.ottorohenkohl.bloggin.zero.extension.pipe
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.HttpHeaders
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.MediaType.WILDCARD
import jakarta.ws.rs.core.UriInfo
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestResponse
import java.util.*

@Path("/listings")
internal class ListingResource(val listingService: ListingService) {

    @Path("/{uuid}/order/{first}/{second}")
    @PATCH
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@RestPath uuid: UUID, @RestPath first: UUID, @RestPath second: UUID, @Context uriInfo: UriInfo): RestResponse<WidgetDTO> {
        return listingService.swap(Reference(uuid), Reference(first), Reference(second)) pipe { RestResponse.seeOther(uriInfo.path(it, WidgetResource::class)) }
    }

    @Path("/{uuid}")
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun post(@RestPath uuid: UUID, @Context uriInfo: UriInfo, widgetDTO: WidgetDTO): RestResponse<WidgetDTO> {
        return listingService.store(Reference(uuid), widgetDTO) pipe { RestResponse.seeOther(uriInfo.path(it, WidgetResource::class)) }
    }

    @Path("/{uuid}/image")
    @POST
    @Consumes(WILDCARD)
    @Produces(APPLICATION_JSON)
    fun post(@RestPath uuid: UUID, @Context headers: HttpHeaders, @Context uriInfo: UriInfo, bytes: List<Byte>): RestResponse<WidgetDTO> {
        return listingService.store(Reference(uuid), ImageDTO(bytes, headers.contentType())) pipe { RestResponse.seeOther(uriInfo.path(it, WidgetResource::class)) }
    }
}