package dev.rohenkohl.bloggin.zero.domain.service.transfer

import dev.rohenkohl.bloggin.zero.domain.service.transfer.Reference.Content
import org.hibernate.query.Page

open class Range<I>(number: Int, size: Int) {

    private val number = if (number >= 0) number else 10
    private val size = if (size >= 1) size else 10

    fun fill(payload: List<Content<I>>): Excerpt<I> = Excerpt(payload, number, payload.size)
    fun page(): Page = Page.page(size, number)

    class Excerpt<I>(val payload: List<Content<I>>, number: Int, size: Int) : Range<I>(number, size)

    companion object {
        operator fun <I> invoke(page: Page) = Range<I>(page.number, page.size)
    }
}