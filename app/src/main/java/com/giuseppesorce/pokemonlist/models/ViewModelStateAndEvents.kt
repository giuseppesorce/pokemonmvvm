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

    class ShowPokemonDetail(var pokemon:Pokemon):HomeListEvents()

}

sealed class DetailState{

    class PokemonDetail(  var id: String,
                          var name: String,
                          var image: String,
                          var url: String,
                          var height: Int, var weight:Int,
                          var abilities:List<Ability>?=null) :DetailState()


}
sealed class DetailEvents {

}
