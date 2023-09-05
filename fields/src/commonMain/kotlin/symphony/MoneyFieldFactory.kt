package symphony

import neat.ValidationFactory
import kotlin.reflect.KMutableProperty0

fun Fields<*>.money(
    name: KMutableProperty0<Double?>,
    label: String = name.name,
    hint: String = label,
    visibility: Visibility = Visibility.Visible,
    onChange: Changer<Double>? = null,
    factory: ValidationFactory<Double>? = null
): NumberField<Double> = double(name, label, hint, visibility, onChange, factory)