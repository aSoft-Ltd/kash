package presenters.changes

import kash.Money
import kotlin.jvm.JvmName
import kotlin.jvm.JvmSynthetic

@Deprecated("use kash instead")
@JvmSynthetic
@JvmName("toShortString")
fun ChangeBox<Money>?.toString() = when (this) {
    null -> ""
    else -> "${previous.toFormattedString()} / ${current.toFormattedString()}"
}