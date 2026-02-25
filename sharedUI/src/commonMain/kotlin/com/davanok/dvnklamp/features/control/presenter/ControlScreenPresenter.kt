package com.davanok.dvnklamp.features.control.presenter

import androidx.compose.runtime.Composable
import com.davanok.dvnklamp.features.control.state.ControlScreenState
import com.davanok.dvnklamp.navigation.Route
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.AppScope

@CircuitInject(Route.Control::class, AppScope::class)
class ControlScreenPresenter: Presenter<ControlScreenState> {
    @Composable
    override fun present(): ControlScreenState = object : ControlScreenState {

    }
}