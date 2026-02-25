package com.davanok.dvnklamp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.davanok.dvnklamp.navigation.Route
import com.davanok.dvnklamp.theme.AppTheme
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import dev.zacsweers.metro.Inject

@Inject
class AppClass(private val circuit: Circuit) {
    @Composable
    operator fun invoke(onThemeChanged: (isDark: Boolean) -> Unit = {}) =
        App(circuit, onThemeChanged)
}

@Composable
private fun App(circuit: Circuit, onThemeChanged: (isDark: Boolean) -> Unit) {
    AppTheme(onThemeChanged) {
        CircuitCompositionLocals(circuit) {
            Surface(modifier = Modifier.fillMaxSize()) {
                CircuitContent(Route.Control, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
