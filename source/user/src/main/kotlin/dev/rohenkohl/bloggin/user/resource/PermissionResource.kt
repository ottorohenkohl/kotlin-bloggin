package dev.rohenkohl.bloggin.user.resource

import dev.rohenkohl.bloggin.user.domain.service.PermissionService
import dev.rohenkohl.bloggin.user.domain.interceptor.binding.Itself
import dev.rohenkohl.bloggin.user.domain.service.transfer.PermissionTransfer
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role.*
import dev.rohenkohl.bloggin.zero.domain.interceptor.binding.Permitted
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.pipe
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.UriInfo
import org.jboss.resteasy.reactive.RestPath
import org.jboss.resteasy.reactive.RestResponse
import java.util.*

@Path("/user/permissions")
internal class PermissionResource(val permissionService: PermissionService) {

    @Path("/{uuid}")
    @GET
    @Itself
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun find(@RestPath uuid: UUID): RestResponse<PermissionTransfer> =
        permissionService.find(Reference(uuid)) pipe { RestResponse.ok(it.payload) }

    @Path("/{uuid}")
    @PATCH
    @Itself
    @Permitted(ADMIN)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@RestPath uuid: UUID, @Context uriInfo: UriInfo, permissionTransfer: PermissionTransfer): RestResponse<PermissionTransfer> =
        permissionService.change(Content(permissionTransfer, uuid)) pipe { RestResponse.seeOther(uriInfo.requestUri) }
}