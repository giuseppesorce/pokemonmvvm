package com.giuseppesorce.pokemonlist.models.mappers


import com.giuseppesorce.data.network.api.responses.PokemonDataResponse
import com.giuseppesorce.data.network.api.responses.PokemonListResponse
import com.giuseppesorce.pokemonlist.models.Ability
import com.giuseppesorce.pokemonlist.models.Pokemon
import com.giuseppesorce.pokemonlist.models.PokemonDetail
import javax.inject.Inject

class UIMapper @Inject constructor() {

    private var BASE_URL_IMAGE = "https://pokeres.bastionbot.org/images/pokemon/"

    fun getPokemonList(response: PokemonListResponse?): List<Pokemon> {

        return response?.let { resp ->

            resp.results?.map {

                Pokemon(
                    id = getId(it.url),
                    name = it.name.capitalize(),
                    image = "${BASE_URL_IMAGE}${getId(it.url)}.png",
                    url = it.url
                )
            }


        } ?: kotlin.run {

            emptyList<Pokemon>()
        }


    }

    private fun getId(url: String): String {
        var ids = url.split("/").filter { it.length > 0 }
        if (ids.size > 0) {
            return ids.get(ids.size - 1)
        }
        return ""
    }

    fun getPokemonData(
        response: PokemonDataResponse?,
        pokemon: Pokemon
    ): PokemonDetail? {

        response?.let { resp ->

            return PokemonDetail(
                id = resp.id.toString() ?: "",
                name = resp.name?.capitalize() ?: "",
                url = resp.specie?.url ?: "",
                image = pokemon.image,
                weight = resp.weight ?: 0,
                height = resp.height ?: 0,
                abilities = resp.abilities?.map {
                    Ability(name = it.ability.name, url = it.ability.url ?: "")
                }
            )
        }


        return null

    }


}