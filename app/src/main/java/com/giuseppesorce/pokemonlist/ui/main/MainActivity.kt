package com.giuseppesorce.pokemonlist.ui.main

import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.giuseppesorce.architecture.base.BaseActivityViewBinding
import com.giuseppesorce.architecture.base.BaseViewModel
import com.giuseppesorce.pokemonlist.databinding.ActivityMainBinding
import com.giuseppesorce.pokemonlist.di.Injector
import com.giuseppesorce.pokemonlist.models.MainEvents
import com.giuseppesorce.pokemonlist.models.MainState

class MainActivity : BaseActivityViewBinding<MainState, MainEvents>() {


    private lateinit var binding: ActivityMainBinding

    private val activityViewModel: MainViewModel by lazy {
        ViewModelProviders.of(
            this,
            Injector.get().factoryMainViewModel()
        ).get(MainViewModel::class.java)
    }


    override fun provideBaseViewModel(): BaseViewModel<MainState, MainEvents>? = activityViewModel

    override fun handleState(state: MainState) {}

    override fun handleEvent(event: MainEvents) {


    }

    override fun setupUI() {
    }

    override fun getDataBindingiView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }
}