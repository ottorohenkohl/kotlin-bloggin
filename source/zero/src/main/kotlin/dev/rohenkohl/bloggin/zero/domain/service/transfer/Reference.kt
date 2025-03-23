package dev.rohenkohl.bloggin.zero.domain.service.transfer

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import java.util.*

open class Reference<out T>(val uuid: UUID) {

    class Content<out T>(val payload: T, uuid: UUID) : Reference<T>(uuid)

    companion object {
        operator fun <I : Identifiable> invoke(identifiable: I) = Reference<I>(identifiable.uuid)
    }
}