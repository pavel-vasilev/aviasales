package com.pvasilev.aviasales.presentation.location

import com.google.android.gms.maps.model.LatLng
import com.pvasilev.aviasales.data.models.City
import com.pvasilev.aviasales.presentation.base.BaseMvRxViewModel

class LocationViewModel(initialState: LocationState) : BaseMvRxViewModel<LocationState>(initialState) {
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
    }
}