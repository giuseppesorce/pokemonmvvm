package com.giuseppesorce.pokemonlist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giuseppesorce.pokemonlist.R
import com.giuseppesorce.pokemonlist.models.Ability
import com.giuseppesorce.pokemonlist.models.Pokemon


import kotlin.properties.Delegates



class PokemonAbilityAdapter(abilities: List<Ability> = emptyList()) :
    RecyclerView.Adapter<PokemonAbilityAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(
            R.layout.item_ability,
            parent,
            false
        )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = allAbility.size
    var allAbility by Delegates.observable(abilities) { _, _, _ -> notifyDataSetChanged() }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var view = holder.bindItems(allAbility[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView by lazy { itemView.findViewById<TextView>(R.id.tvName)}
        fun bindItems(item: Ability): View {
            tvName.text= item.name

            return itemView
        }
    }
}