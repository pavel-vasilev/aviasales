package com.pvasilev.aviasales.presentation.base

import com.airbnb.mvrx.MvRxState

interface ViewModelFactory<V : BaseMvRxViewModel<S>, S : MvRxState> {
    fun create(initialState: S): V
}