package dev.rohenkohl.bloggin.page.resource

import dev.rohenkohl.bloggin.page.domain.service.SitemapService
import dev.rohenkohl.bloggin.page.domain.service.transfer.SitemapTransfer
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Minimum
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role.ADMIN
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Permitted
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.UriInfo
import org.jboss.resteasy.reactive.RestResponse
import java.util.*

@Path("/page/sitemap")
internal class SitemapResource(val sitemapService: SitemapService) {

    @Path("/")
    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun get(): RestResponse<SitemapTransfer> =
        sitemapService.find() pipe { RestResponse.ok(it.payload) }

    @Path("/")
    @PATCH
    @Minimum
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@Context uriInfo: UriInfo, sitemapTransfer: SitemapTransfer): RestResponse<SitemapTransfer> =
        sitemapService.change(Content(sitemapTransfer, UUID.randomUUID())) pipe { RestResponse.seeOther(uriInfo.requestUri) }
}