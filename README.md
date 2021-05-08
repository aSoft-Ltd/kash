# Kash

![badge][badge-maven] ![badge][badge-mpp] ![badge][badge-android] ![badge][badge-js] ![badge][badge-jvm] ![badge][badge-ios]

## Introduction

A Multiplatform lib for handling money and currency

## Setup : Gradle

```kotlin
dependencies {
    implementation("tz.co.asoft:kash-core:0.0.10")
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

[badge-maven]: https://img.shields.io/maven-central/v/tz.co.asoft/live-core/0.0.30?style=flat

[badge-mpp]: https://img.shields.io/badge/kotlin-multiplatform-blue?style=flat

[badge-android]: http://img.shields.io/badge/platform-android-brightgreen.svg?style=flat

[badge-js]: http://img.shields.io/badge/platform-js-yellow.svg?style=flat

[badge-jvm]: http://img.shields.io/badge/platform-jvm-orange.svg?style=flat

[badge-ios]: http://img.shields.io/badge/platform-ios-silver.svg?style=flat
