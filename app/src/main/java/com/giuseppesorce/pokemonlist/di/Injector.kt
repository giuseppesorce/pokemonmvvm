package com.giuseppesorce.pokemonlist.di

import com.giuseppesorce.pokemonlist.AppApplication


class Injector {
    companion object {
        fun get() = AppApplication.applicationComponent
    }
}