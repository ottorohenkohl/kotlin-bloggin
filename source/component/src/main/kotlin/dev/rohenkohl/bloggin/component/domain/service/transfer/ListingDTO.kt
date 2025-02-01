package dev.rohenkohl.bloggin.component.domain.service.transfer

import dev.rohenkohl.bloggin.component.domain.model.constant.Alignment
import dev.rohenkohl.bloggin.component.domain.model.constant.Direction
import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content

data class ListingDTO(val across: Alignment?, val direction: Direction?, val elements: List<Content<WidgetDTO>>?, val main: Alignment?) : WidgetDTO