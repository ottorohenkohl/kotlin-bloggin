package dev.rohenkohl.bloggin.page.domain.service.transfer

import dev.rohenkohl.bloggin.component.domain.service.transfer.WidgetTransfer
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content

data class SiteTransfer(val role: Role?, val name: String?, val visible: Boolean?, val key: String?, val widget: Content<WidgetTransfer>?)