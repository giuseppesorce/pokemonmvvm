package com.giuseppesorce.pokemonlist.models



sealed class GenericState
sealed class GenericEvents


sealed class SplashState
sealed class SplashEvents {
    object GotoList:SplashEvents()
}

sealed class MainState
sealed class MainEvents {


}

sealed class HomeListState
sealed class HomeListEvents {

}

sealed class DetailState
sealed class DetailEvents {

}
