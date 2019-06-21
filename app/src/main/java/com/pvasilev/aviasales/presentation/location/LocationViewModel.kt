package com.pvasilev.aviasales.presentation.location

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.google.android.gms.maps.model.LatLng
import com.pvasilev.aviasales.data.models.City
import com.pvasilev.aviasales.presentation.base.BaseMvRxViewModel
import com.pvasilev.aviasales.presentation.base.ViewModelFactory
import com.pvasilev.aviasales.presentation.map.MapArgs
import com.pvasilev.aviasales.presentation.map.MapScreen
import com.pvasilev.aviasales.presentation.search.OnCitySelectedListener
import com.pvasilev.aviasales.presentation.search.SearchScreen
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import toothpick.config.Module

class LocationViewModel @AssistedInject constructor(
    @Assisted initialState: LocationState,
    private val router: Router
) : BaseMvRxViewModel<LocationState>(initialState) {
    fun onLocationFromClicked() {
        selectCity(object : OnCitySelectedListener {
            override fun onCitySelected(city: City) {
                setState {
                    copy(
                        locationFrom = LatLng(city.location.lat.toDouble(), city.location.lon.toDouble()),
                        cityFrom = city.latinCity
                    )
                }
            }
        })
    }

    fun onLocationToClicked() {
        selectCity(object : OnCitySelectedListener {
            override fun onCitySelected(city: City) {
                setState {
                    copy(
                        locationTo = LatLng(city.location.lat.toDouble(), city.location.lon.toDouble()),
                        cityTo = city.latinCity
                    )
                }
            }
        })
    }

    fun onSwapClicked() {
        setState {
            copy(locationFrom = locationTo, locationTo = locationFrom, cityFrom = cityTo, cityTo = cityFrom)
        }
    }

    fun onSearchClicked() {
        withState { state ->
            if (state.locationFrom != state.locationTo) {
                router.navigateTo(
                    MapScreen(
                        MapArgs(
                            state.locationFrom!!,
                            state.locationTo!!,
                            state.cityFrom!!.take(3).toUpperCase(),
                            state.cityTo!!.take(3).toUpperCase()
                        )
                    )
                )
            }
        }
    }

    fun onBackPressed() {
        router.exit()
    }

    private fun selectCity(onCitySelectedListener: OnCitySelectedListener) {
        val scope = Toothpick.openScopes("AppScope", "SearchScope")
        val module = Module().apply {
            bind(OnCitySelectedListener::class.java).toInstance(onCitySelectedListener)
        }
        scope.installModules(module)
        router.navigateTo(SearchScreen())
    }

    @AssistedInject.Factory
    interface Factory : ViewModelFactory<LocationViewModel, LocationState>

    companion object : MvRxViewModelFactory<LocationViewModel, LocationState> {
        override fun create(viewModelContext: ViewModelContext, state: LocationState): LocationViewModel? {
            val factory = (viewModelContext as FragmentViewModelContext).fragment<LocationFragment>().viewModelFactory
            return factory.create(state)
        }
    }
}