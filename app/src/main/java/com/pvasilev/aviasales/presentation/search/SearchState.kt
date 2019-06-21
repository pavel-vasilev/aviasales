package com.pvasilev.aviasales.presentation.search

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.pvasilev.aviasales.data.models.City

data class SearchState(val cities: Async<List<City>> = Uninitialized) : MvRxState