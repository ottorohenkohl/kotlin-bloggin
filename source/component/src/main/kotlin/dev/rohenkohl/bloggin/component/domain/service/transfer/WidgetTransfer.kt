package dev.rohenkohl.bloggin.component.domain.service.transfer

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME

@JsonTypeInfo(use = NAME, include = PROPERTY, property = "type")
@JsonSubTypes(
    Type(HeaderTransfer::class, name = "Header"),
    Type(IconTransfer::class, name = "Icon"),
    Type(ImageTransfer::class, name = "Image"),
    Type(ListingTransfer::class, name = "Listing"),
    Type(ParagraphTransfer::class, name = "Paragraph"),
    Type(SeparatorTransfer::class, name = "Separator"),
)
sealed interface WidgetTransfer
