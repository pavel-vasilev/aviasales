package com.pvasilev.aviasales.presentation.location

import com.airbnb.mvrx.MvRxState
import com.google.android.gms.maps.model.LatLng

data class LocationState(
    val locationFrom: LatLng? = null,
    val locationTo: LatLng? = null,
    val cityFrom: String? = null,
    val cityTo: String? = null
) : MvRxState