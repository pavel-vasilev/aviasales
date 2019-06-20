package com.pvasilev.aviasales.presentation.base

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.pvasilev.aviasales.BuildConfig

abstract class BaseMvRxViewModel<S : MvRxState>(initialState: S) : BaseMvRxViewModel<S>(initialState, BuildConfig.DEBUG)