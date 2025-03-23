package dev.rohenkohl.bloggin.component.resource

import dev.rohenkohl.bloggin.component.domain.service.WidgetService
import dev.rohenkohl.bloggin.component.domain.interceptor.binding.Owner
import dev.rohenkohl.bloggin.component.domain.service.transfer.WidgetTransfer
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Minimum
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role.*
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Permitted
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.path
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.UriInfo
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestResponse
import java.util.*

@Path("/component/widgets")
internal class WidgetResource(val widgetService: WidgetService) {

    @Path("/{uuid}")
    @DELETE
    @Owner
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun delete(@RestPath uuid: UUID): RestResponse<Unit> =
        widgetService.erase(Reference(uuid)) pipe { RestResponse.accepted() }

    @Path("/{uuid}")
    @GET
    @Minimum
    @Permitted(MANAGER)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(@RestPath uuid: UUID): RestResponse<WidgetTransfer> =
        widgetService.find(Reference(uuid)) pipe { RestResponse.ok(it.payload) }

    @Path("/{uuid}")
    @PATCH
    @Owner
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@RestPath uuid: UUID, @Context uriInfo: UriInfo, widgetTransfer: WidgetTransfer): RestResponse<WidgetTransfer> =
        widgetService.change(Content(widgetTransfer, uuid)) pipe { RestResponse.seeOther(uriInfo.path(it, WidgetResource::class)) }
}