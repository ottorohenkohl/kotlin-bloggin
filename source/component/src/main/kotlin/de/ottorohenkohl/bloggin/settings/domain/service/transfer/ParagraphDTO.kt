package de.ottorohenkohl.bloggin.settings.domain.service.transfer

import de.ottorohenkohl.bloggin.settings.domain.model.constant.Size

data class ParagraphDTO(val size: Size?, val text: String?) : WidgetDTO