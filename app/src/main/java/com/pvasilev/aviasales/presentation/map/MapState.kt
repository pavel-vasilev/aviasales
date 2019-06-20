package com.pvasilev.aviasales.presentation.map

import com.airbnb.mvrx.MvRxState
import com.google.android.gms.maps.model.LatLng

data class MapState(
    val from: LatLng,
    val to: LatLng
) : MvRxState {
    constructor(args: MapArgs) : this(args.from, args.to)
}