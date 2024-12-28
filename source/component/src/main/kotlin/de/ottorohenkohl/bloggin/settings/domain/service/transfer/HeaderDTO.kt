package de.ottorohenkohl.bloggin.settings.domain.service.transfer

import de.ottorohenkohl.bloggin.settings.domain.model.constant.Level

data class HeaderDTO(val level: Level?, val text: String?) : WidgetDTO