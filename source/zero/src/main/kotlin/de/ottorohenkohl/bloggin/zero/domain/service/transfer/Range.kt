package de.ottorohenkohl.bloggin.zero.domain.service.transfer

import org.hibernate.query.Page
import de.ottorohenkohl.bloggin.zero.domain.service.transfer.Reference.Content

interface Range<I> {

    val number: Int
    val size: Int

    fun fill(payload: List<Content<I>>): Excerpt<I> = Range(Page.page(payload.size, number), payload)
    fun page(): Page = Page.page(size, number)

    interface Excerpt<I> : Range<I> {

        val payloads: List<Content<I>>
    }

    companion object {
        operator fun <I> invoke(page: Page): Range<I> {
            return object : Range<I> {
                override val number by page::number
                override val size by page::size
            }
        }

        operator fun <I> invoke(page: Page, payloads: List<Content<I>>): Excerpt<I> {
            return object : Excerpt<I> {
                override val number by page::number
                override val size by payloads::size

                override val payloads = payloads
            }
        }
    }
}