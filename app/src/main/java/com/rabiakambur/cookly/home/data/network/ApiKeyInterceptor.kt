package com.rabiakambur.cookly.home.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalUrl = originalRequest.url
        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter(API_KEY_QUERY, apiKey)
            .build()
        val requestWithApiKey: Request = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()
        return chain.proceed(requestWithApiKey)
    }

    companion object {
        private const val API_KEY_QUERY = "apiKey"
    }
}