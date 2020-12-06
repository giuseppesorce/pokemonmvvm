package com.giuseppesorce.domain.interacors

import com.giuseppesorce.data.extension.Result
import com.giuseppesorce.data.network.api.responses.PokemonDataResponse
import com.giuseppesorce.data.network.api.responses.PokemonListResponse
import com.giuseppesorce.data.repositories.PokemonRepository
import com.giuseppesorce.domain.BuildConfig
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class PokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository){
    suspend operator fun invoke(offset:Int, limit:Int): Result<PokemonListResponse> {
        val a = BuildConfig.BUILD_TYPE

        return pokemonRepository.getPokemonList(Pair(offset, limit))
    }
}


class PokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository){
    suspend operator fun invoke(id:String ): Result<PokemonDataResponse> {
        return pokemonRepository.getPokemonDetail(id)
    }
}
