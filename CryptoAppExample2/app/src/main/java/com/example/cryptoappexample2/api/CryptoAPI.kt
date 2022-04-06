package com.example.cryptoappexample.api

import com.example.cryptoappexample.model.Crypto
import io.reactivex.Single
import retrofit2.http.GET

interface CryptoAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/
    // IA32-CryptoComposeData/main/cryptolist.json
    @GET("IA32-CryptoComposeData/main/cryptolist.json")

    fun getCrypto() : Single<List<Crypto>>
}