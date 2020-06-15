package com.giuseppesorce.pokemonlist.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giuseppesorce.architecture.base.BaseViewBindingFragment
import com.giuseppesorce.architecture.base.BaseViewModel
import com.giuseppesorce.pokemonlist.R
import com.giuseppesorce.pokemonlist.databinding.FragmentDetailBinding
import com.giuseppesorce.pokemonlist.di.Injector
import com.giuseppesorce.pokemonlist.models.DetailEvents
import com.giuseppesorce.pokemonlist.models.DetailState
import com.giuseppesorce.pokemonlist.ui.adapters.PokemonAbilityAdapter
import com.giuseppesorce.pokemonlist.utils.extensions.colorSpanText


class DetailFragment : BaseViewBindingFragment<DetailState, DetailEvents>() {

    private lateinit var binding: FragmentDetailBinding
    private val pokemonAbilityAdapter: PokemonAbilityAdapter by lazy {
        PokemonAbilityAdapter(
            emptyList()
        )
    }

    private val fragmentViewModel: DetailViewModel by lazy {
        ViewModelProviders.of(
            this,
            Injector.get().factoryDetailViewModel()
        ).get(DetailViewModel::class.java)
    }

    val args: DetailFragmentArgs by navArgs()

    override fun provideBaseViewModel(): BaseViewModel<DetailState, DetailEvents>? =
        fragmentViewModel

    override fun setupUI() {
        activity?.let {
            setStatusBarColor(ContextCompat.getColor(it, R.color.blu_background))
        }
        activity?.let {
            binding.toolBar.setNavigationIcon(ContextCompat.getDrawable(it, R.drawable.ic_back))
        }

        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.rvList.layoutManager = LinearLayoutManager(
            activity?.applicationContext,
            RecyclerView.VERTICAL, false
        )
        binding.rvList.adapter = pokemonAbilityAdapter

    }

    override fun handleState(state: DetailState) {

        when (state) {
            is DetailState.PokemonDetail -> {
                setPokemon(state)
            }
        }
    }

    private fun setPokemon(pokemon: DetailState.PokemonDetail) {
        activity?.let {
            Glide.with(it.applicationContext).load(pokemon.image).into(binding.ivImage)
            binding.tvName.text = pokemon.name
            var altezza= getString(R.string.altezza)
            var weight= getString(R.string.weight)

            binding.tvAltezza.text = "$altezza: ${pokemon.height}"
            binding.tvAltezza.colorSpanText(
                binding.tvAltezza.text.toString(),
                pokemon.height.toString(),
                ContextCompat.getColor(it.applicationContext, R.color.blu),
                true
            )
            binding.tvWeight.text = "$weight: ${pokemon.weight}"
            binding.tvWeight.colorSpanText(
                binding.tvWeight.text.toString(),
                pokemon.weight.toString(),
                ContextCompat.getColor(it.applicationContext, R.color.blu),
                true
            )
            pokemonAbilityAdapter.allAbility = pokemon.abilities ?: emptyList()
        }
    }

    override fun handleEvent(event: DetailEvents) {
    }

    override fun initFragment() {
        args.pokemon?.let { pokemon ->
            fragmentViewModel.setPokemon(pokemon)
        }
    }

    override fun showIdleState() {
        binding.ivLogoAnim.pauseAnimation()
        binding.ivLogoAnim.visibility = View.GONE
    }

    override fun showLoadingState() {
        binding.ivLogoAnim.playAnimation()
        binding.ivLogoAnim.visibility = View.VISIBLE

    }

    override fun setFragmentViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewFrag = binding.root
    }
}