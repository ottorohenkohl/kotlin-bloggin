package dev.rohenkohl.bloggin.user.domain.exception

class LoginExistsException(cause: Throwable? = null) : RuntimeException(cause) {

    override val message: String = "Login does already exist"
}