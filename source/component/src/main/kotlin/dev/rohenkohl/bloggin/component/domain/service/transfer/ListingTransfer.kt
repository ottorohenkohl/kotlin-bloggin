package dev.rohenkohl.bloggin.component.domain.service.transfer

import dev.rohenkohl.bloggin.component.domain.model.constant.Alignment
import dev.rohenkohl.bloggin.component.domain.model.constant.Direction
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content

data class ListingTransfer(val across: Alignment?, val direction: Direction?, val main: Alignment?, val elements: List<Content<WidgetTransfer>>?) : WidgetTransfer