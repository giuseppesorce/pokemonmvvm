package com.giuseppesorce.data.repositories


import com.giuseppesorce.data.extension.Result
import com.giuseppesorce.data.extension.resultAwait
import com.giuseppesorce.data.network.api.PokemonServiceApi
import com.giuseppesorce.data.network.api.responses.PokemonListResponse

import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonApi: PokemonServiceApi) {

    suspend fun getPokemonList(offset:Int, limit:Int): Result<PokemonListResponse> {
        return pokemonApi.getPokemon(offset.toString(), limit.toString()).resultAwait()
    }


}
