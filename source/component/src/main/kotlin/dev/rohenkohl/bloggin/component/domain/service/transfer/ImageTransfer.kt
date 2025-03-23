package dev.rohenkohl.bloggin.component.domain.service.transfer

data class ImageTransfer(val mimetype: String, val data: List<Byte>?) : WidgetTransfer