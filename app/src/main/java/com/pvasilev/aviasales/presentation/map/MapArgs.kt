package com.pvasilev.aviasales.presentation.map

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MapArgs(
    val locationFrom: LatLng,
    val locationTo: LatLng,
    val cityFrom: String,
    val cityTo: String
) : Parcelable