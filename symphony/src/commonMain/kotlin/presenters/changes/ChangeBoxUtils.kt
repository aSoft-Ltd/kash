package presenters.changes

import kotlin.jvm.JvmName

@Deprecated("use kash instead")
@JvmName("toString")
fun <D> ChangeBox<D>?.toString() = when (this) {
    null -> ""
    else -> "$previous/$current"
}