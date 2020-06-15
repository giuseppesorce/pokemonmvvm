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

class PokemonDataResponse{
  var name:String?=null
  var id:Int?=null
  var order:Int?=null
  var weight:Int?=null
  var height:Int?=null
  var base_experience:Int?=null
  var specie:Specie?=null
  var abilities:List<AbilityMain>?=null
}

data class PokemonInList(var name:String, var url:String)
data class AbilityMain(var is_hidden:Boolean, var ability:Ability)
data class Ability(var url:String, var name:String)
data class Specie(var url:String, var name:String)