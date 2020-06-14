package com.giuseppesorce.pokemonlist.models.mappers



import com.giuseppesorce.data.network.api.responses.PokemonListResponse
import com.giuseppesorce.pokemonlist.models.Pokemon
import javax.inject.Inject

class UIMapper @Inject constructor() {

    private var BASE_URL_IMAGE= "https://pokeres.bastionbot.org/images/pokemon/"

    fun getPokemonList(response: PokemonListResponse?): List<Pokemon> {

      return  response?.let {resp->

          resp.results?.map {

              Pokemon(name = it.name, image = "${BASE_URL_IMAGE}${getId(it.url)}.png", url = it.url )
          }



        } ?: kotlin.run {

            emptyList<Pokemon>()
        }


    }

    private fun getId(url: String): String {
        var ids= url.split("/").filter { it.length > 0 }
        if(ids.size >0){
            return ids.get(ids.size -1)
        }
        return ""
    }


}