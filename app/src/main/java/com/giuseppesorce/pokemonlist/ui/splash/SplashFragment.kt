package com.giuseppesorce.pokemonlist.ui.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.giuseppesorce.architecture.base.BaseViewBindingFragment
import com.giuseppesorce.architecture.base.BaseViewModel
import com.giuseppesorce.pokemonlist.R
import com.giuseppesorce.pokemonlist.databinding.FragmentSplashBinding
import com.giuseppesorce.pokemonlist.di.Injector
import com.giuseppesorce.pokemonlist.models.SplashEvents
import com.giuseppesorce.pokemonlist.models.SplashState


class SplashFragment : BaseViewBindingFragment<SplashState, SplashEvents>() {

    private lateinit var binding: FragmentSplashBinding
    private val fragmentViewModel: SplashViewModel by lazy {
        ViewModelProviders.of(
            this,
            Injector.get().factorySplashViewModel()
        ).get(SplashViewModel::class.java)
    }

    override fun provideBaseViewModel(): BaseViewModel<SplashState, SplashEvents>? = fragmentViewModel

    override fun setupUI() {
        activity?.let {
            setStatusBarColor(ContextCompat.getColor(it, R.color.white))
        }

    }

    override fun handleState(state: SplashState) {
    }

    override fun handleEvent(event: SplashEvents) {
        when(event){
            is SplashEvents.GotoList->{
                findNavController().navigate(R.id.action_splashFragment_to_homeListFragment)
            }
        }
    }

    override fun setFragmentViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        viewFrag = binding.root
    }
}