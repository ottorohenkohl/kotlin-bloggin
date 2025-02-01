package dev.rohenkohl.bloggin.person.extension

class LoginExistsException : Exception() {
    override val message: String = "Login does already exist."
}

class PersonExistsException : Exception() {
    override val message: String = "Person does already exist."
}