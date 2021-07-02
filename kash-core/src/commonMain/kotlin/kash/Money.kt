    @file:UseSerializers(LongAsStringSerializer::class)
    
    package kash
    
    import kotlinx.serialization.Serializable
    import kotlinx.serialization.UseSerializers
    import kotlinx.serialization.builtins.LongAsStringSerializer
    import kotlin.jvm.JvmStatic
    import kotlin.jvm.JvmSynthetic
    import kotlin.math.floor

    @Serializable
    data class Money internal constructor(
        /** In the lowest denomination */
        val amount: ULong,
        val currency: Currency
    ) {
       
        companion object {
    
            @JvmStatic
            fun of(amount: UInt, currency: Currency) = Money((amount * currency.lowestDenomination).toULong(), currency)
           

            @JvmStatic
            fun of(amount: ULong, currency: Currency) = Money((amount * currency.lowestDenomination).toULong(), currency)
           

            @JvmStatic
            fun of(amount: Double, currency: Currency) = Money((amount * currency.lowestDenomination.toShort()).toULong(), currency)
           
    
     
            @JvmStatic
            @JvmSynthetic
            fun of(amount: Int, currency: Currency) = Money((amount * currency.lowestDenomination.toShort()).toULong(), currency)
           

            @JvmStatic
            @JvmSynthetic
            fun of(amount: Long, currency: Currency) = Money((amount * currency.lowestDenomination.toShort()).toULong(), currency)
           
        }
        val readableValue get() = amount.toDouble() / currency.lowestDenomination.toDouble()
        
           val readableString
get() = (currency.name + " " + if (readableValue - floor(readableValue) == 0.0) "$readableValue.0" else readableValue).replace(
    ".0.0",
    ".0"
)
    }