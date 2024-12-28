package de.ottorohenkohl.bloggin.person.domain.annotation.value

enum class Role {
    AUTHOR, MANAGER, ADMIN;

    infix fun matches(role: Role?): Boolean {
        return if (role == null) false else this <= role
    }
}