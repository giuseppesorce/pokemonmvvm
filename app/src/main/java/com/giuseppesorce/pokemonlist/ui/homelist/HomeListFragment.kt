package com.giuseppesorce.pokemonlist.ui.homelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.giuseppesorce.architecture.base.BaseViewBindingFragment
import com.giuseppesorce.architecture.base.BaseViewModel
import com.giuseppesorce.pokemonlist.databinding.FragmentHomeListBinding
import com.giuseppesorce.pokemonlist.di.Injector
import com.giuseppesorce.pokemonlist.models.HomeListEvents
import com.giuseppesorce.pokemonlist.models.HomeListState


class HomeListFragment : BaseViewBindingFragment<HomeListState, HomeListEvents>() {

    private lateinit var binding: FragmentHomeListBinding
    private val fragmentViewModel: HomeListViewModel by lazy {
        ViewModelProviders.of(
            this,
            Injector.get().factoryHomeListViewModel()
        ).get(HomeListViewModel::class.java)
    }

    override fun provideBaseViewModel(): BaseViewModel<HomeListState, HomeListEvents>? = fragmentViewModel

    override fun setupUI() {

    }

    override fun handleState(state: HomeListState) {

    }

    override fun handleEvent(event: HomeListEvents) {
    }

    override fun initFragment() {

        fragmentViewModel.loadPokemon()
    }

    override fun setFragmentViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentHomeListBinding.inflate(inflater, container, false)
        viewFrag = binding.root
    }
}