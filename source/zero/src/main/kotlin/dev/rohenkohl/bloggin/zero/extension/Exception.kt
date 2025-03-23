package dev.rohenkohl.bloggin.zero.extension

import io.quarkus.logging.Log


inline fun <reified T : Throwable, R> fail(function: () -> R, throwable: Throwable) = try {
    function(); throw throwable
} catch (any: Throwable) {
    Log.debug(any)

    if (any is T) null else throw throwable
}

inline fun <reified T : Throwable, R> rethrow(function: () -> R, throwableBuilder: (T) -> Throwable) = try {
    function()
} catch (any: Throwable) {
    Log.debug(any)

    if (any is T) throw throwableBuilder(any) else function()
}