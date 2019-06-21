package com.pvasilev.aviasales.presentation.location

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class LocationScreen : SupportAppScreen() {
    override fun getFragment(): Fragment = LocationFragment()
}