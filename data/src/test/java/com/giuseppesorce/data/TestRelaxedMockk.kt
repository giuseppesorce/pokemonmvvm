package com.giuseppesorce.data

import com.giuseppesorce.data.network.api.PokemonServiceApi
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class TestRelaxedMockk {

    @RelaxedMockK
    lateinit var pokemonServiceApi: PokemonServiceApi

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Test relaxed mockk`() {
        val mockedOggetto = mockk<Oggetto>(relaxed = true)


        checkNotNull(pokemonServiceApi.getPokemon("", ""))
    }

}