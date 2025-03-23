package dev.rohenkohl.bloggin.component.domain.service.transfer

import dev.rohenkohl.bloggin.component.domain.model.Widget
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content

data class OwnershipTransfer(val layout: Content<LayoutTransfer>?, val widget: Reference<Widget>?)