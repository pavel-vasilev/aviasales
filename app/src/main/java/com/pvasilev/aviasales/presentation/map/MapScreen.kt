package com.pvasilev.aviasales.presentation.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MvRx
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MapScreen(private val args: MapArgs) : SupportAppScreen() {
    override fun getFragment(): Fragment = MapFragment().apply {
        arguments = Bundle().apply {
            putParcelable(MvRx.KEY_ARG, args)
        }
    }
}