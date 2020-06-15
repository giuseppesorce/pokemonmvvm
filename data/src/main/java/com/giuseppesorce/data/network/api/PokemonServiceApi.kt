package com.giuseppesorce.data.network.api



import com.giuseppesorce.data.network.api.responses.PokemonDataResponse
import com.giuseppesorce.data.network.api.responses.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Giuseppe Sorce
 */
interface PokemonServiceApi {

    @GET("pokemon/")
    fun getPokemon(@Query("offset") offset: String, @Query("limit") limit: String  ) : Call<PokemonListResponse>


    @GET("pokemon/{id}")
    fun getPokemonDetail(@Path("id") id:String  ) : Call<PokemonDataResponse>
}

