package com.giuseppesorce.pokemonlist.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.giuseppesorce.architecture.base.BaseViewBindingFragment
import com.giuseppesorce.architecture.base.BaseViewModel
import com.giuseppesorce.pokemonlist.databinding.FragmentDetailBinding
import com.giuseppesorce.pokemonlist.di.Injector
import com.giuseppesorce.pokemonlist.models.SplashEvents
import com.giuseppesorce.pokemonlist.models.SplashState
import com.giuseppesorce.pokemonlist.ui.splash.SplashViewModel


class DetailFragment : BaseViewBindingFragment<SplashState, SplashEvents>() {

    private lateinit var binding: FragmentDetailBinding
    private val fragmentViewModel: SplashViewModel by lazy {
        ViewModelProviders.of(
            this,
            Injector.get().factorySplashViewModel()
        ).get(SplashViewModel::class.java)
    }

    override fun provideBaseViewModel(): BaseViewModel<SplashState, SplashEvents>? = fragmentViewModel

    override fun setupUI() {

    }

    override fun handleState(state: SplashState) {

    }

    override fun handleEvent(event: SplashEvents) {

    }

    override fun initFragment() {

    }

    override fun setFragmentViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewFrag = binding.root
    }
}