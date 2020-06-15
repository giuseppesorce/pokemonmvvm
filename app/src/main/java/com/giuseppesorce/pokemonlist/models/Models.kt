package com.giuseppesorce.pokemonlist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Giuseppe Sorce
 */
@Parcelize
data class Pokemon(var id: String, var name: String, var image: String, var url: String) :
    Parcelable

@Parcelize
data class PokemonDetail(
    var id: String,
    var name: String,
    var image: String,
    var url: String,
    var height: Int, var weight:Int,
    var abilities:List<Ability>?=null
    ) :
    Parcelable



@Parcelize
data class Ability(var name:String, var url:String):Parcelable