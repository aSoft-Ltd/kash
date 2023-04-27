package presenters.numerics

@Deprecated("use kash instead")
inline val Number.pct get() = Percentage(toDouble())