package com.example.movieapp.core.data.remote

import com.example.movieapp.BuildConfig
import com.example.movieapp.core.util.Constants
import okhttp3.Interceptor
import okhttp3.Response


class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter(Constants.LANGUAGE_PARAM, Constants.LANGUAGE_VALUE)
            .addQueryParameter(Constants.API_KEY_PARAM, BuildConfig.API_KEY)
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .header("Cache-Control", "no-cache, no-store, must-revalidate")
            .header("Pragma", "no-cache") 
            .header("Expires", "0") 
            .build()

        return chain.proceed(newRequest)
    }
}
