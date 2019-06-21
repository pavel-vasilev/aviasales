package com.pvasilev.aviasales.presentation

import toothpick.Toothpick
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T> inject(scope: String) = object : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return Toothpick.openScope(scope).getInstance(T::class.java)
    }
}