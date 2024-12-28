package de.ottorohenkohl.bloggin.settings.domain.service.transfer

import de.ottorohenkohl.bloggin.settings.domain.model.constant.Alignment
import de.ottorohenkohl.bloggin.settings.domain.model.constant.Direction
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content

data class ListingDTO(val across: Alignment?, val direction: Direction?, val elements: List<Content<WidgetDTO>>?, val main: Alignment?) : WidgetDTO