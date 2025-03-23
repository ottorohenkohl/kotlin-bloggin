package dev.rohenkohl.bloggin.page.resource

import dev.rohenkohl.bloggin.page.domain.interceptor.binding.Managing
import dev.rohenkohl.bloggin.page.domain.interceptor.binding.Visible
import dev.rohenkohl.bloggin.page.domain.service.SiteService
import dev.rohenkohl.bloggin.page.domain.service.transfer.PreviewTransfer
import dev.rohenkohl.bloggin.page.domain.service.transfer.SiteTransfer
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Minimum
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role.ADMIN
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Permitted
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role.MANAGER
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

@Path("/page/sites")
internal class SiteResource(val siteService: SiteService) {

    @Path("/{uuid}")
    @DELETE
    @Managing
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun delete(@RestPath uuid: UUID): RestResponse<Unit> =
        siteService.erase(Reference(uuid)) pipe { RestResponse.accepted() }

    @Path("/")
    @GET
    @Minimum
    @Permitted(MANAGER)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(@RestQuery number: Int, @RestQuery size: Int): RestResponse<Excerpt<PreviewTransfer>> =
        siteService.find(Range(number, size)) pipe { RestResponse.ok(it) }

    @Path("/{uuid}")
    @GET
    @Visible
    @Permitted(MANAGER)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(@RestPath uuid: UUID): RestResponse<SiteTransfer> =
        siteService.find(Reference(uuid)) pipe { RestResponse.ok(it.payload) }

    @Path("/{uuid}")
    @PATCH
    @Managing
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@RestPath uuid: UUID, @Context uriInfo: UriInfo, siteTransfer: SiteTransfer): RestResponse<SiteTransfer> =
        siteService.change(Content(siteTransfer, uuid)) pipe { RestResponse.seeOther(uriInfo.requestUri) }

    @Path("/")
    @POST
    @Minimum
    @Permitted(MANAGER)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun post(@Context uriInfo: UriInfo, siteTransfer: SiteTransfer): RestResponse<Unit> =
        siteService.store(siteTransfer) pipe { RestResponse.seeOther(uriInfo.path(it)) }
}