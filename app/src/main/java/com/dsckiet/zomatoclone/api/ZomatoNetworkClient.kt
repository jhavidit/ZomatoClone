package com.dsckiet.zomatoclone.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException


object ZomatoNetworkClient {
    const val BASE_URL = "https://developers.zomato.com/api/v2.1/"
    const val API_KEY = "f5cd87e4deca15ff2a49d74ce3328e1b"

    fun getClient(): ZomatoNetworkInterface {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request =
                    chain.request().newBuilder().addHeader("user-key", API_KEY).build()
                return chain.proceed(request)
            }
        })

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()


        val retrofitService: ZomatoNetworkInterface by lazy {
            retrofit.create(ZomatoNetworkInterface::class.java)
        }
        return retrofitService
    }
}