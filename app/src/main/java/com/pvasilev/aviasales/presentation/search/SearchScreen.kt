package com.pvasilev.aviasales.presentation.search

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SearchScreen : SupportAppScreen() {
    override fun getFragment(): Fragment = SearchFragment()
}