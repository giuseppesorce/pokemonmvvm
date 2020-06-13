package com.giuseppesorce.pokemonlist.di.modules


import com.giuseppesorce.data.network.api.PokemonServiceApi
import com.giuseppesorce.pokemonlist.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * @author Giuseppe Sorce
 */

@Module
class NetworkModule(private val baseUrl:String) {


    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        var builder = OkHttpClient.Builder()
            .connectTimeout(160, TimeUnit.SECONDS)
            .readTimeout(160, TimeUnit.SECONDS)
            .writeTimeout(160, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder = builder.addInterceptor(getLoggingInterceptor())
        }
        return builder.build()
    }

    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Timber.tag("SERVER").d(message)

        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideApiRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    @Provides
    @Singleton
    fun providePokemonService( retrofit: Retrofit): PokemonServiceApi {
        return retrofit.create(PokemonServiceApi::class.java)
    }

}