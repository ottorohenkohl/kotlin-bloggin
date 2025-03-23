package dev.rohenkohl.bloggin.zero.domain.exception

class SizeNotPositiveException(cause: Throwable? = null) : RuntimeException(cause) {

    override val message: String = "Size should be positive"
}