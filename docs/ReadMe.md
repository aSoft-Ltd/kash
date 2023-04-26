# Kash

[badges]

## Introduction

A Multiplatform lib for handling money and currency

## Setup : Gradle

```kotlin
dependencies {
    implementation("tz.co.asoft:kash-money:[version]")
}
```

## Samples

```kotlin
val money = 3.4.USD
println(money.readableString) // "USD 3.4"

val currency = Currency.GBP

println(currency.symbol) // £
```

## Development

Information was taken from [here](https://github.com/mhs/world-currencies/blob/master/currencies.json), so please go and
start that repo

## Api Reference
The full api reference of kase can be found at [https://asoft-ltd.github.io/kash](https://asoft-ltd.github.io/kash)

## Support

There are multiple ways you can support this project

### Star It

If you found it useful, just give it a star

### Contribute

You can help by submitting pull request to available open tickets on the issues section

### Report Issues

This makes it easier to catch bugs and offer enhancements required

## Credits

- [andylamax](https://github.com/andylamax) - The author