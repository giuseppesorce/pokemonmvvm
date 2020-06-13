package com.giuseppesorce.architecture

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import dagger.Lazy
import javax.inject.Inject

class InjectionViewModelProvider<V> @Inject constructor(
    private val lazyViewModel: Lazy<V>
) {

    @Suppress("UNCHECKED_CAST")
    val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = lazyViewModel.get() as T
    }

    inline fun <reified V : ViewModel, reified F : Fragment> provide(fragment: F) =
        ViewModelProviders.of(fragment, factory).get(V::class.java)
}