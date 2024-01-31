package com.example.myapplication.PersonApi.remote

import com.example.myapplication.PersonApi.model.PersonResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Retrofit-Schnittstelle zum Definieren von API-Anfragemethoden
interface RandomPersonApiService {
    // GET-Anfrage an die API-Endpunkt "api/"
    @GET("api/")
    suspend fun getPerson(): PersonResult
}

// Basis-URL für die Random User Generator API
const val BASE_URL = "https://randomuser.me/"

// Moshi-Objekt für die JSON-Serialisierung und Deserialisierung
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit-Objekt für die Netzwerkanfragen
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Objekt, das die Retrofit-Schnittstelle bereitstellt
object PersonApi {
    // Lazy-initialisierte Instanz der Retrofit-Schnittstelle
    val retrofitService: RandomPersonApiService by lazy { retrofit.create(RandomPersonApiService::class.java) }
}
