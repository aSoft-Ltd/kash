package presenters.internal

import kash.MoneyFormatter

@Deprecated("use kash instead")
@PublishedApi
internal val DEFAULT_FORMATTER = MoneyFormatter(abbreviate = false)