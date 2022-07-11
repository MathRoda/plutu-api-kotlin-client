# Plutu Payments Gateway API Kotlin Client

[![Version](https://img.shields.io/badge/Version-1.5.0-blue.svg)](https://github.com/MathRoda/plutu-api-kotlin-client/releases/tag/1.5.0)
[![Platform](https://img.shields.io/badge/Platform-Android-blue.svg?style=flat)](https://developer.android.com/about/)
![Kotlin 1.6.21](https://img.shields.io/badge/Kotlin-1.7.0-orange.svg)

This library is written in Kotlin and provides a way to Implement Payments Gateway from [Plutu.ly API](https://docs.plutu.ly/api-documentation/introduction).
It uses [Retrofit](https://square.github.io/retrofit/), and [Kotlin coroutines](https://kotlinlang.org/docs/coroutines-overview.html).

## Installation
1. Add the following lines in your AndroidManifest.xml file
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

2. Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```
3. Add the dependency
```gradle
dependencies {
	        implementation 'com.github.MathRoda:plutu-api-kotlin-client:Tag'
	}
```
4. Create a new account to get your API keys [Plutu](https://my.plutus.ly/register/)
![](media/register.png)

## Usage
[Plutu](https://plutu.ly) provides online payments gateways with Sadad / Edfali / Local Banks Cards.

Instantiate Retrofit service and proceed with the call:

```kotlin
private val retrofitBuilder =  Retrofit.Builder()
    .baseUrl(BaseUrl.PLUTU_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    
object InstancePlutuService {
    val sadadService: SadadService by lazy {
        retrofitBuilder.create()
    }

    val adfaliService: AdfaliService by lazy {
        retrofitBuilder.create()
    }
}

val SadadPaymentGateway = InstancePlutuService.sadadService
    /**
     * this Function should be always called within a coroutine
     */
    .sendOtp(
        token = "Your Token",
        apiKey = "Your API Key",
        mobileNumber = "Sadad Mobile Number",
        birthYear = "Year of Birth",
        amount = "Amount in Libyan Dinars"
    )
```

### for more detailed implementation with structure handling and use cases read source code  
