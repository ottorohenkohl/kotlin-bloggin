package dev.rohenkohl.bloggin.component.resource

import dev.rohenkohl.bloggin.component.domain.service.ListingService
import dev.rohenkohl.bloggin.component.domain.interceptor.binding.Owner
import dev.rohenkohl.bloggin.component.domain.service.transfer.ImageTransfer
import dev.rohenkohl.bloggin.component.domain.service.transfer.WidgetTransfer
import dev.rohenkohl.bloggin.component.extension.contentType
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role.ADMIN
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Permitted
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.extension.path
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.HttpHeaders
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.MediaType.WILDCARD
import jakarta.ws.rs.core.UriInfo
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestResponse
import java.util.*

@Path("/component/listings")
internal class ListingResource(val listingService: ListingService) {

    @Path("/{uuid}/order/{first}/{second}")
    @PATCH
    @Owner
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@RestPath uuid: UUID, @RestPath first: UUID, @RestPath second: UUID, @Context uriInfo: UriInfo): RestResponse<WidgetTransfer> =
        listingService.swap(Reference(uuid), Reference(first), Reference(second)) pipe { RestResponse.seeOther(uriInfo.path(it, WidgetResource::class)) }

    @Path("/{uuid}")
    @POST
    @Owner
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun post(@RestPath uuid: UUID, @Context uriInfo: UriInfo, widgetTransfer: WidgetTransfer): RestResponse<WidgetTransfer> =
        listingService.store(Reference(uuid), widgetTransfer) pipe { RestResponse.seeOther(uriInfo.path(it, WidgetResource::class)) }

    @Path("/{uuid}/image")
    @POST
    @Owner
    @Permitted(ADMIN)
    @Consumes(WILDCARD)
    @Produces(APPLICATION_JSON)
    fun post(@RestPath uuid: UUID, @Context headers: HttpHeaders, @Context uriInfo: UriInfo, bytes: List<Byte>): RestResponse<WidgetTransfer> =
        listingService.store(Reference(uuid), ImageTransfer(headers.contentType(), bytes)) pipe { RestResponse.seeOther(uriInfo.path(it, WidgetResource::class)) }
}