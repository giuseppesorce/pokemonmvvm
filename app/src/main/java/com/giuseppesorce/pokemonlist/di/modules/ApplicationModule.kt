package com.giuseppesorce.pokemonlist.di.modules

import android.content.Context
import com.giuseppesorce.pokemonlist.AppApplication


import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val mainApplication: AppApplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context {
        return mainApplication
    }

}