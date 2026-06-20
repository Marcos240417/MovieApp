package com.example.movieapp.core.di

import com.example.movieapp.BuildConfig
import com.example.movieapp.core.data.remote.MovieService
import com.example.movieapp.core.data.remote.ParamsInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module 
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val TIMEOUT_SECONDS = 15L


val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
    }

    factory { ParamsInterceptor() }

    factory {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<ParamsInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    single<MovieService> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get()) 
            .addConverterFactory(
                get<Json>().asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(MovieService::class.java)
    }
}
