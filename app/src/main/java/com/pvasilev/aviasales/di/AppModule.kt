package com.pvasilev.aviasales.di

import com.pvasilev.aviasales.data.repository.AutocompleteService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class AppModule : Module() {
    init {
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java)
        bind(Moshi::class.java).toProvider(MoshiProvider::class.java)
        bind(Retrofit::class.java).toProvider(RetrofitProvider::class.java)
        bind(AutocompleteService::class.java).toProvider(AutocompleteServiceProvider::class.java)
    }
}