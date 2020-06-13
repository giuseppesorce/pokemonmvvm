package com.giuseppesorce.pokemonlist.di.components


import android.content.Context
import com.giuseppesorce.architecture.ViewModelFactory
import com.giuseppesorce.pokemonlist.di.modules.ApplicationModule
import com.giuseppesorce.pokemonlist.di.modules.NetworkModule
import com.giuseppesorce.pokemonlist.ui.detail.DetailViewModel
import com.giuseppesorce.pokemonlist.ui.homelist.HomeListViewModel
import com.giuseppesorce.pokemonlist.ui.main.MainViewModel
import com.giuseppesorce.pokemonlist.ui.splash.SplashViewModel

import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class ))
interface ApplicationComponent {

    fun context(): Context


    fun factoryMainViewModel(): ViewModelFactory<MainViewModel>
    fun factorySplashViewModel(): ViewModelFactory<SplashViewModel>
    fun factoryHomeListViewModel(): ViewModelFactory<HomeListViewModel>
    fun factoryDetailViewModel(): ViewModelFactory<DetailViewModel>

}
