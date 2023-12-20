package com.example.myapplication.PersonApi.remote

import com.example.myapplication.PersonApi.model.PersonResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface RandomPersonApiService {
    @GET("api/")
    suspend fun getPerson(): PersonResult
}


const val BASE_URL = "https://randomuser.me/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object PersonApi {
    val retrofitService: RandomPersonApiService by lazy { retrofit.create(RandomPersonApiService::class.java) }
}
