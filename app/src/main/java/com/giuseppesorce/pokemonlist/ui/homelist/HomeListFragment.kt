package com.giuseppesorce.pokemonlist.ui.homelist

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.giuseppesorce.architecture.base.BaseViewBindingFragment
import com.giuseppesorce.architecture.base.BaseViewModel
import com.giuseppesorce.pokemonlist.R
import com.giuseppesorce.pokemonlist.databinding.FragmentHomeListBinding
import com.giuseppesorce.pokemonlist.di.Injector
import com.giuseppesorce.pokemonlist.models.HomeListEvents
import com.giuseppesorce.pokemonlist.models.HomeListState
import com.giuseppesorce.pokemonlist.models.Pokemon
import com.giuseppesorce.pokemonlist.ui.adapters.PokemonListAdapter


class HomeListFragment : BaseViewBindingFragment<HomeListState, HomeListEvents>() {

    private val pokemonListAdapter: PokemonListAdapter by lazy {
        PokemonListAdapter(
            emptyList()
        )
    }

    private lateinit var binding: FragmentHomeListBinding
    private val fragmentViewModel: HomeListViewModel by lazy {
        ViewModelProviders.of(
            this,
            Injector.get().factoryHomeListViewModel()
        ).get(HomeListViewModel::class.java)
    }

    override fun provideBaseViewModel(): BaseViewModel<HomeListState, HomeListEvents>? =
        fragmentViewModel

    override fun setupUI() {
        activity?.let {
            setStatusBarColor(ContextCompat.getColor(it, R.color.blu_background))
        }

        binding.toolBar.setTitle(getString(R.string.thepokemon))
        binding.rvList.layoutManager = GridLayoutManager(
            activity?.applicationContext,
           2
        )
        binding.rvList.adapter = pokemonListAdapter
        pokemonListAdapter.onActionClickListener = { item: Pokemon, position: Int ->
            fragmentViewModel.onSelectPokemon(item)
        }

        binding.toolBar.inflateMenu(R.menu.homelist)
    }

    override fun observerData() {
        fragmentViewModel.allPokemonsLD.observe(this, Observer {pokemons->
            pokemonListAdapter.allPokemonns= pokemons

        })
    }

    override fun showIdleState() {
        binding.ivLogoAnim.pauseAnimation()
        binding.ivLogoAnim.visibility= View.GONE
    }

    override fun showLoadingState() {
        binding.ivLogoAnim.playAnimation()
        binding.ivLogoAnim.visibility= View.VISIBLE

    }

    override fun handleState(state: HomeListState) {
    }

    override fun handleEvent(event: HomeListEvents) {

        when(event){

            is HomeListEvents.ShowPokemonDetail->{
                findNavController().navigate(R.id.action_homeListFragment_to_detailFragment,   bundleOf("pokemon" to event.pokemon))
            }
        }
    }

    override fun initFragment() {
        fragmentViewModel.loadPokemon()
    }

    override fun setFragmentViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentHomeListBinding.inflate(inflater, container, false)
        viewFrag = binding.root
    }
}