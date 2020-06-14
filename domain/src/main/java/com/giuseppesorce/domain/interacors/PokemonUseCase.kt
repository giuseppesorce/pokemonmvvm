package com.giuseppesorce.domain.interacors

import com.giuseppesorce.data.extension.Result
import com.giuseppesorce.data.network.api.responses.PokemonListResponse
import com.giuseppesorce.data.repositories.PokemonRepository
import javax.inject.Inject

/**
 * @author Giuseppe Sorce
 */
class PokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository){
    suspend operator fun invoke(offset:Int, limit:Int): Result<PokemonListResponse> {
        return pokemonRepository.getPokemonList(offset, limit)
    }
}
