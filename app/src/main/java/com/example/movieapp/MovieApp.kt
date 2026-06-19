package com.example.movieapp

import android.app.Application
import com.example.movieapp.core.di.networkModule // ✅ Importando o módulo refatorado
import com.example.movieapp.movie_popular_feature.di.moviePopularFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            // ✅ Sênior: Adiciona o logger padrão do Koin para ajudar a debugar injeções falhas
            androidLogger()
            // ✅ Sênior: Garante que o contexto do app esteja disponível no grafo (ex: se o Room ou DataStore precisarem de Context)
            androidContext(this@MovieApp)

            // Carrega o módulo de rede do aplicativo
            modules(
                networkModule,
                moviePopularFeatureModule
            )
        }
    }
}