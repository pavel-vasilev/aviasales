package com.pvasilev.aviasales.presentation.location

import com.google.android.gms.maps.model.LatLng
import com.pvasilev.aviasales.data.models.City
import com.pvasilev.aviasales.presentation.base.BaseMvRxViewModel
import com.pvasilev.aviasales.presentation.map.MapArgs
import com.pvasilev.aviasales.presentation.map.MapScreen
import com.pvasilev.aviasales.presentation.search.SearchScreen
import ru.terrakok.cicerone.Router

class LocationViewModel(
    initialState: LocationState,
    private val router: Router
) : BaseMvRxViewModel<LocationState>(initialState) {
    private var onCitySelectedListener: ((City) -> Unit)? = null

    fun onCitySelected(city: City) {
        onCitySelectedListener?.invoke(city)
    }

    fun onLocationFromClicked() {
        onCitySelectedListener = {
            setState {
                copy(
                    locationFrom = LatLng(it.location.lat.toDouble(), it.location.lon.toDouble()),
                    cityFrom = it.latinCity
                )
            }
        }
        router.navigateTo(SearchScreen())
    }

    fun onLocationToClicked() {
        onCitySelectedListener = {
            setState {
                copy(
                    locationTo = LatLng(it.location.lat.toDouble(), it.location.lon.toDouble()),
                    cityTo = it.latinCity
                )
            }
        }
        router.navigateTo(SearchScreen())
    }

    fun onSearchClicked() {
        withState { state ->
            if (state.locationFrom != state.locationTo) {
                router.navigateTo(
                    MapScreen(
                        MapArgs(
                            state.locationFrom!!,
                            state.locationTo!!,
                            state.cityFrom!!,
                            state.cityTo!!
                        )
                    )
                )
            }
        }
    }
}