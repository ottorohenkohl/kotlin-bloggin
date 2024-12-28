package de.ottorohenkohl.bloggin.settings.ressource

import de.ottorohenkohl.bloggin.settings.domain.service.transfer.WidgetDTO
import de.ottorohenkohl.bloggin.settings.domain.service.WidgetService
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference
import de.ottorohenkohl.bloggin.zero.extension.path
import de.ottorohenkohl.bloggin.zero.extension.pipe
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.UriInfo
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestResponse
import java.util.*

@Path("/widgets")
internal class WidgetResource(val widgetService: WidgetService) {

    @Path("/{uuid}")
    @DELETE
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun delete(@RestPath uuid: UUID): RestResponse<Unit> {
        return widgetService.erase(Reference(uuid)) pipe { RestResponse.accepted() }
    }

    @Path("/{uuid}")
    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(@RestPath uuid: UUID): RestResponse<WidgetDTO> {
        return widgetService.find(Reference(uuid)) pipe { RestResponse.ok(it.payload) }
    }

    @Path("/{uuid}")
    @PATCH
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@RestPath uuid: UUID, @Context uriInfo: UriInfo, widgetDTO: WidgetDTO): RestResponse<WidgetDTO> {
        return widgetService.change(Reference(widgetDTO, uuid)) pipe { RestResponse.seeOther(uriInfo.path(it, WidgetResource::class)) }
    }
}