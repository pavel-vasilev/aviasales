package com.pvasilev.aviasales.presentation.map

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.pvasilev.aviasales.R
import com.pvasilev.aviasales.presentation.OnBackPressedListener
import com.pvasilev.aviasales.presentation.base.BaseMapMvRxFragment
import com.pvasilev.aviasales.presentation.inject

class MapFragment : BaseMapMvRxFragment(), OnBackPressedListener {
    val viewModelFactory: MapViewModel.Factory by inject("AppScope")

    private val viewModel: MapViewModel by fragmentViewModel()

    private var marker: Marker? = null

    private var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMapAsync { map = it }
    }

    override fun invalidate() {
        val map = map ?: return
        withState(viewModel) { state ->
            map.apply {
                marker?.apply {
                    position = state.planeLocation
                    rotation = state.planeRotation
                } ?: run {
                    addPolyline(getPolyline(state.pointsOnPath))
                    addMarker(getCityMarker(state.cityFrom, state.locationFrom))
                    addMarker(getCityMarker(state.cityTo, state.locationTo))
                    moveCamera(CameraUpdateFactory.newLatLng(state.locationFrom))
                    marker = addMarker(getPlaneMarker(state.planeLocation, state.planeRotation))
                }
            }
        }
    }

    override fun onBackPressed(): Boolean {
        viewModel.onBackPressed()
        return true
    }

    private fun getPolyline(points: List<LatLng>) = PolylineOptions().apply {
        addAll(points)
        pattern(listOf(Dot(), Gap(20.0F), Dot(), Gap(20.0F)))
    }

    private fun getCityMarker(city: String, position: LatLng) = MarkerOptions().apply {
        flat(true)
        position(position)
        anchor(0.5F, 0.5F)
        icon(getCityIcon(city))
    }

    private fun getPlaneMarker(position: LatLng, rotation: Float) = MarkerOptions().apply {
        flat(true)
        position(position)
        rotation(rotation)
        anchor(0.5F, 0.5F)
        zIndex(1.0F)
        icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_plane))
    }

    @SuppressLint("InflateParams")
    private fun getCityIcon(city: String): BitmapDescriptor {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.view_marker, null) as TextView
        return with(view) {
            text = city
            measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            layout(0, 0, measuredWidth, measuredHeight)
            val bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_4444)
            val canvas = Canvas(bitmap)
            draw(canvas)
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}