package com.giuseppesorce.domain.interacors

import com.giuseppesorce.data.extension.NetworkError
import com.giuseppesorce.data.extension.Result
import com.giuseppesorce.data.repositories.PokemonRepository
import com.giuseppesorce.domain.BuildConfig
import com.giuseppesorce.domain.CoroutinesTestRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("EXPERIMENTAL_API_USAGE")
class PokemonListUseCaseTest {

    lateinit var pokemonListUseCase: PokemonListUseCase

    @MockK
    lateinit var pokemonRepository: PokemonRepository

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        pokemonListUseCase = PokemonListUseCase(pokemonRepository)
    }


    @Test
    fun `Test PokemonListUseCase with an api exception`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            mockkStatic(BuildConfig::class)
            every { BuildConfig.BUILD_TYPE } returns "ciaone"


            // Given
            coEvery { pokemonRepository.getPokemonList(any()) } returns Result.Error(
                NetworkError.AuthFailed("Password wrong", null),
                null
            )

            // When
            val result = pokemonListUseCase.invoke(5, 9)

            // Then
            assert(result is Result.Error)
            // Per intercettare argomenti di oggetti mocked capture()
            val slot = slot<Pair<Int, Int>>()
            coVerify(exactly = 1) { pokemonRepository.getPokemonList(capture(slot)) }

            // o utilizzi eq() per verificare gli argomenti passati a un oggetto mocckato SE sono proprieta' primitive int/string etc

            // se vuoi verificare gli argomenti passati che sono un oggetto complesso, usi capture()

            assertEquals(5, slot.captured.first)
            assertEquals(9, slot.captured.second)
        }



    @Test
    fun `Test PokemonListUseCase with an api success but an empty`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

        }

}