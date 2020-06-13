package com.giuseppesorce.pokemonlist

import android.app.Application
import com.giuseppesorce.pokemonlist.di.components.ApplicationComponent
import com.giuseppesorce.pokemonlist.di.components.DaggerApplicationComponent
import com.giuseppesorce.pokemonlist.di.modules.ApplicationModule
import com.giuseppesorce.pokemonlist.di.modules.NetworkModule


/**
 * @author Giuseppe Sorce
 */
@Suppress("DEPRECATION")
class AppApplication : Application(){

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))

            .build()
    }
}
