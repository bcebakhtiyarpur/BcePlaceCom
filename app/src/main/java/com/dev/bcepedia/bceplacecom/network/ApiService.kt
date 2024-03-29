package com.dev.bcepedia.bceplacecom.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = ""
private val moshi = Moshi.Builder()
  .add(KotlinJsonAdapterFactory())
  .build()

private val retrofit = Retrofit.Builder()
  .addCallAdapterFactory(CoroutineCallAdapterFactory())
  .addConverterFactory(MoshiConverterFactory.create(moshi))
  .baseUrl(BASE_URL)
  .build()

interface ApiService{
//  @GET("{field}")
//  fun getProperties():
//        Deferred<List<ApiProperty>>
}

object AppApi{
  val retrofitService: ApiService by lazy{
    retrofit.create(ApiService::class.java)
  }
}