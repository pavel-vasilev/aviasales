package com.pvasilev.aviasales.domain

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil

class GetPointsOnPathUseCase {
    operator fun invoke(params: Params): List<LatLng> {
        val (from, to, size) = params
        return (0..size)
            .map { it / size.toDouble() }
            .map {
                SphericalUtil.interpolate(from, to, it)
            }
    }

    data class Params(val from: LatLng, val to: LatLng, val size: Int)
}