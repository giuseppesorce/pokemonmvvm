package com.giuseppesorce.data.network.api.responses

/**
 * @author Giuseppe Sorce
 */

class PokemonListResponse{
    var count:Int?=null
    var next:String?=null
    var previous:String?=null
    var results:List<PokemonInList>?=null

}

data class PokemonInList(var name:String, var url:String)