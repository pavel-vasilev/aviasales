package com.pvasilev.aviasales.di

import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Provider

class MoshiProvider @Inject constructor() : Provider<Moshi> {
    override fun get(): Moshi {
        return Moshi.Builder()
            .add(Wrapped.ADAPTER_FACTORY)
            .build()
    }
}