package com.encora.topsongs.network.api

import retrofit2.Retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object APIClientBuilder {

    private const val BASE_URL = "http://ax.itunes.apple.com";

    fun buildAPI(): APIClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            /*.addConverterFactory(JaxbConverterFactory.create())*/
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(APIClient::class.java)
    }
}