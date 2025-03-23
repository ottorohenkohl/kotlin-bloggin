package dev.rohenkohl.bloggin.zero.domain.exception

class NumberNegativeException(cause: Throwable? = null) : RuntimeException(cause) {

    override val message: String = "Number should not be negative"
}