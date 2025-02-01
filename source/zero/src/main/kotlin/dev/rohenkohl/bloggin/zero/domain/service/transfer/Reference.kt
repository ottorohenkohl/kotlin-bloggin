package dev.rohenkohl.bloggin.zero.domain.service.transfer

import dev.rohenkohl.bloggin.zero.domain.model.Identifiable
import java.util.*

interface Reference<out I> {

    val uuid: UUID

    interface Content<out I> : Reference<I> {

        val payload: I
    }

    companion object {
        operator fun <I> invoke(payload: I, uuid: UUID): Content<I> where I : Any {
            return object : Content<I> {
                override val payload = payload
                override val uuid = uuid
            }
        }

        operator fun <I> invoke(identifiable: I): Reference<I> where I : Identifiable {
            return object : Reference<I> {
                override val uuid = identifiable.uuid
            }
        }

        operator fun <I> invoke(uuid: UUID): Reference<I> where I : Identifiable {
            return object : Reference<I> {
                override val uuid = uuid
            }
        }
    }
}