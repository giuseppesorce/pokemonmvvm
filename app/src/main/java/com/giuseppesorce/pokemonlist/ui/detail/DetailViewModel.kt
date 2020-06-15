package com.giuseppesorce.pokemonlist.ui.detail


import androidx.lifecycle.viewModelScope
import com.giuseppesorce.architecture.base.BaseViewModel
import com.giuseppesorce.data.extension.Result
import com.giuseppesorce.domain.interacors.PokemonDetailUseCase
import com.giuseppesorce.pokemonlist.models.*
import com.giuseppesorce.pokemonlist.models.mappers.UIMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(

    private val pokemonDetailUseCase: PokemonDetailUseCase,
    private val uiMapper: UIMapper
) : BaseViewModel<DetailState, DetailEvents>() {


    fun setPokemon(pokemon: Pokemon) {


        showLoading()
        viewModelScope.launch {

            var result = pokemonDetailUseCase.invoke(pokemon.id)
            hideLoading()
            when (result) {

                is Result.Ok -> {
                    var pokemonData: PokemonDetail? = uiMapper.getPokemonData(result.value, pokemon)
                    pokemonData?.let {data->
                        viewState = DetailState.PokemonDetail(
                            data.id,
                            data.name,
                            data.image,
                            data.url,
                            data.height,
                            data.weight,
                            data.abilities
                        )

                    }
                }
                is Result.Error -> {

                }
            }

        }

    }


}