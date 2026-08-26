package com.example.movieapp

import android.app.Application
import com.example.movieapp.core.di.networkModule 
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
            androidLogger()
            androidContext(this@MovieApp)

            modules(
                networkModule,
                moviePopularFeatureModule
            )
        }
    }
}
