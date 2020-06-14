package com.giuseppesorce.pokemonlist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giuseppesorce.pokemonlist.R
import com.giuseppesorce.pokemonlist.models.Pokemon


import kotlin.properties.Delegates



class PokemonListAdapter(pokemons: List<Pokemon> = emptyList()) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(
            R.layout.item_pokemon,
            parent,
            false
        )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = allPokemonns.size
    var onActionClickListener: ((item: Pokemon, position: Int) -> Unit)? = null
    var allPokemonns by Delegates.observable(pokemons) { _, _, _ -> notifyDataSetChanged() }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var view = holder.bindItems(allPokemonns[position])
        view.setOnClickListener {
            onActionClickListener?.invoke(allPokemonns[position], position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView by lazy { itemView.findViewById<TextView>(R.id.tvName)}
        val ivImage: ImageView by lazy { itemView.findViewById<ImageView>(R.id.ivImage)}
        fun bindItems(item: Pokemon): View {
            tvName.text= item.name
            Glide.with(itemView.context).load(item.image).into(ivImage)
            return itemView
        }
    }
}