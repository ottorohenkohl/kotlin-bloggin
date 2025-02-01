package dev.rohenkohl.bloggin.zero.domain.service.transfer

import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import dev.rohenkohl.bloggin.zero.extension.NumberNegativeException
import dev.rohenkohl.bloggin.zero.extension.SizeNotPositiveException
import org.hibernate.query.Page

interface Range<I> {

    val number: Int
    val size: Int

    fun fill(payload: List<Content<I>>): Excerpt<I> = Range(number, payload.size, payload)
    fun page(): Page = Page.page(size, number)

    interface Excerpt<I> : Range<I> {

        val payloads: List<Content<I>>
    }

    companion object {
        operator fun <I> invoke(number: Int, size: Int): Range<I> {
            return object : Range<I> {
                override val number = if (number >= 0) number else throw NumberNegativeException()
                override val size = if (size > 0) size else throw SizeNotPositiveException()
            }
        }

        operator fun <I> invoke(page: Page): Range<I> {
            return object : Range<I> {
                override val number by page::number
                override val size by page::size
            }
        }

        private operator fun <I> invoke(number: Int, size: Int, payloads: List<Content<I>>): Excerpt<I> {
            return object : Excerpt<I> {
                override val number = number
                override val size = size
                override val payloads = payloads
            }
        }
    }
}