package com.giuseppesorce.pokemonlist.ui.splash


import android.os.Handler
import com.giuseppesorce.architecture.base.BaseViewModel
import com.giuseppesorce.pokemonlist.models.MainEvents
import com.giuseppesorce.pokemonlist.models.Pokemon
import com.giuseppesorce.pokemonlist.models.SplashEvents
import com.giuseppesorce.pokemonlist.models.SplashState
import javax.inject.Inject

class SplashViewModel @Inject constructor(

) : BaseViewModel<SplashState, SplashEvents>() {


    private var PAUSE = 2700L

    init {
        Handler().postDelayed({
            emitEvent(SplashEvents.GotoList)
        }, PAUSE)
    }

}