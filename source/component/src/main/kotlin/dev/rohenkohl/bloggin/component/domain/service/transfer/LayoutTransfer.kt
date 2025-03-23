package dev.rohenkohl.bloggin.component.domain.service.transfer

import dev.rohenkohl.bloggin.component.domain.model.Widget
import dev.rohenkohl.bloggin.zero.domain.model.constant.Role
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference

data class LayoutTransfer(val role: Role?, val visible: Boolean?, val widget: Reference<Widget>?)