package com.pvasilev.aviasales

import android.os.Bundle
import android.os.Looper
import com.airbnb.mvrx.BaseMvRxActivity
import com.pvasilev.aviasales.presentation.OnBackPressedListener
import com.pvasilev.aviasales.presentation.inject
import com.pvasilev.aviasales.presentation.location.LocationScreen
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class MainActivity : BaseMvRxActivity() {
    private val navigatorHolder: NavigatorHolder by inject("AppScope")

    private val router: Router by inject("AppScope")

    private val navigator: Navigator = object : SupportAppNavigator(this, R.id.container) {
        override fun applyCommands(commands: Array<out Command>?) {
            if (Thread.currentThread() != Looper.getMainLooper().thread) {
                runOnUiThread {
                    super.applyCommands(commands)
                }
            } else {
                super.applyCommands(commands)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            router.newRootScreen(LocationScreen())
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        val onBackPressedListener = supportFragmentManager.findFragmentById(R.id.container) as? OnBackPressedListener
        onBackPressedListener?.onBackPressed() ?: super.onBackPressed()
    }
}
