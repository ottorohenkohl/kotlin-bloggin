package dev.rohenkohl.bloggin.zero.extension

class NumberNegativeException : Exception() {
    override val message: String = "Number should not be negative"
}

class SizeNotPositiveException : Exception() {
    override val message: String = "Size should be positive"
}
