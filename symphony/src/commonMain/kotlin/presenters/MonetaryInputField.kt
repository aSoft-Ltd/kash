@file:JsExport

package presenters

import kash.Monetary
import kotlin.js.JsExport

@Deprecated("use kash instead")
interface MonetaryInputField : MonetaryValueInputField<Monetary>