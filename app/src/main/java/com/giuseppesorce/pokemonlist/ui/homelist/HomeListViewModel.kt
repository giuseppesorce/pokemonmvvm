package com.giuseppesorce.pokemonlist.ui.homelist


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.giuseppesorce.architecture.base.BaseViewModel
import com.giuseppesorce.data.extension.Result
import com.giuseppesorce.domain.interacors.PokemonListUseCase
import com.giuseppesorce.pokemonlist.models.HomeListEvents
import com.giuseppesorce.pokemonlist.models.HomeListState
import com.giuseppesorce.pokemonlist.models.Pokemon
import com.giuseppesorce.pokemonlist.models.mappers.UIMapper
import kotlinx.coroutines.launch
import javax.inject.Inject
class HomeListViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase,
    private var uiMapper: UIMapper

) : BaseViewModel<HomeListState, HomeListEvents>() {
    var offset = 20
    var limit = 20
    var pokemons : List<Pokemon>?= null
    var allPokemonsLD= MutableLiveData<List<Pokemon>>()

    fun loadPokemon() {

        showLoading()
        viewModelScope.launch {
            var response = pokemonListUseCase.invoke(offset, limit)
            hideLoading()
            when(response){

                is Result.Ok -> {
                    var pokemonList = uiMapper.getPokemonList(response.value)
                    allPokemonsLD.value= pokemonList
                }
                is Result.Error ->{

                }
            }
        }
    }

    fun onSelectPokemon(pokemon: Pokemon) {
        emitEvent(HomeListEvents.ShowPokemonDetail(pokemon))
    }


}