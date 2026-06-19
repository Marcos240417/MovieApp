package com.example.movieapp.core.di

import com.example.movieapp.BuildConfig
import com.example.movieapp.core.data.remote.MovieService
import com.example.movieapp.core.data.remote.ParamsInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module // ✅ Importando a DSL do Koin
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val TIMEOUT_SECONDS = 15L

// ✅ Sênior: Módulos do Koin devem ser declarados como variáveis globais ou instâncias 'module'
val networkModule = module {

    // Singleton do Json do Kotlinx Serialization
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
    }

    // Fábrica do Interceptor de Parâmetros (TMDB Key / Language)
    factory { ParamsInterceptor() }

    // Fábrica do Interceptor de Logs (Apenas em Debug)
    factory {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    // Singleton do OkHttpClient (O Koin injeta o Params e o Log automaticamente via 'get()')
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<ParamsInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    // Singleton do MovieService pronto para ser usado nos Repositories
    single<MovieService> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get()) // ✅ O get() busca o OkHttpClient configurado acima
            .addConverterFactory(
                get<Json>().asConverterFactory("application/json".toMediaType()) // ✅ O get() busca o Json configurado no topo
            )
            .build()
            .create(MovieService::class.java)
    }
}