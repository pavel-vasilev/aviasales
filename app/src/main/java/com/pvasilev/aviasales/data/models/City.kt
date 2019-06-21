package com.pvasilev.aviasales.data.models

data class City(
    val id: Int,
    val latinCity: String,
    val latinCountry: String,
    val latinFullName: String,
    val countryCode: String,
    val location: Location
)