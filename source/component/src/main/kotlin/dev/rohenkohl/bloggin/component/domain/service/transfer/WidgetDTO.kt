package dev.rohenkohl.bloggin.component.domain.service.transfer

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS

@JsonSubTypes(
    Type(EmptyDTO::class),
    Type(HeaderDTO::class),
    Type(IconDTO::class),
    Type(ImageDTO::class),
    Type(ListingDTO::class),
    Type(ParagraphDTO::class),
    Type(SeparatorDTO::class),
)
@JsonTypeInfo(use = CLASS, include = PROPERTY)
sealed interface WidgetDTO