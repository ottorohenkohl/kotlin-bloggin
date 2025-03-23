package dev.rohenkohl.bloggin.user.domain.exception

class PersonExistsException(cause: Throwable? = null) : RuntimeException(cause) {

    override val message: String = "Person does already exist"
}