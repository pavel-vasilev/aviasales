package com.pvasilev.aviasales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.mvrx.MvRx
import com.google.android.gms.maps.model.LatLng
import com.pvasilev.aviasales.presentation.map.MapArgs
import com.pvasilev.aviasales.presentation.map.MapFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val locationFrom = LatLng(60.169845, 24.938523)
            val locationTo = LatLng(42.69751, 23.32415)
            val cityFrom = "HEL"
            val cityTo = "SOF"
            val args = MapArgs(locationFrom, locationTo, cityFrom, cityTo)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MapFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(MvRx.KEY_ARG, args)
                    }
                })
                .commit()
        }
    }
}
