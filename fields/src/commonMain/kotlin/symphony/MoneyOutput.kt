package symphony

import kash.Currency

class MoneyOutput(
    var currency: Currency?,
    var amount: Double = 0.0
)