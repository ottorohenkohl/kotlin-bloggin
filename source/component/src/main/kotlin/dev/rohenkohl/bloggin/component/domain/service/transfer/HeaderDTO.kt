package dev.rohenkohl.bloggin.component.domain.service.transfer

import dev.rohenkohl.bloggin.component.domain.model.constant.Level

data class HeaderDTO(val level: Level?, val text: String?) : WidgetDTO