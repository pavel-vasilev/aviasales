package com.pvasilev.aviasales.presentation.search

import com.pvasilev.aviasales.data.models.City

interface OnCitySelectedListener {
    fun onCitySelected(city: City)
}