package com.pvasilev.aviasales.presentation.map

import com.airbnb.mvrx.MvRxState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil

data class MapState(
    val locationFrom: LatLng,
    val locationTo: LatLng,
    val cityFrom: String,
    val cityTo: String,
    val planeLocation: LatLng,
    val planeRotation: Float,
    val pointsOnPath: List<LatLng> = emptyList()
) : MvRxState {
    constructor(args: MapArgs) : this(
        args.locationFrom,
        args.locationTo,
        args.cityFrom,
        args.cityTo,
        args.locationFrom,
        (SphericalUtil.computeHeading(args.locationFrom, args.locationTo) - 90).toFloat()
    )
}