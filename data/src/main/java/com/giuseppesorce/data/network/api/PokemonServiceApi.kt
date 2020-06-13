package com.giuseppesorce.data.network.api



import com.giuseppesorce.data.network.api.responses.PokemonListResponse
import retrofit2.http.*

/**
 * @author Giuseppe Sorce
 */
interface PokemonServiceApi {

    @GET("pokemon/")
   suspend fun getPokemon(@Query("offset") offset: String, @Query("limit") limit: String  ) : PokemonListResponse

}

