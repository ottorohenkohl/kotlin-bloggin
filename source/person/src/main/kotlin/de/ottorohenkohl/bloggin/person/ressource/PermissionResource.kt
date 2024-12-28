package de.ottorohenkohl.bloggin.person.ressource

import de.ottorohenkohl.bloggin.person.domain.service.transfer.PermissionDTO
import de.ottorohenkohl.bloggin.person.domain.service.PermissionService
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

@Path("/permission")
internal class PermissionResource(val permissionService: PermissionService) {

    @Path("/{uuid}")
    @DELETE
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun find(@RestPath uuid: UUID): RestResponse<PermissionDTO> {
        return permissionService.find(Reference(uuid)) pipe { RestResponse.ok(it.payload) }
    }

    @Path("/{uuid}")
    @PATCH
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun patch(@RestPath uuid: UUID, @Context uriInfo: UriInfo, permissionDTO: PermissionDTO): RestResponse<PermissionDTO> {
        return permissionService.change(Reference(permissionDTO, uuid)) pipe { RestResponse.seeOther(uriInfo.path(it)) }
    }
}