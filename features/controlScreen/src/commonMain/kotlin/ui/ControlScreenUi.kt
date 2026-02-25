package ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.davanok.dvnklamp.navigation.Route
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dev.zacsweers.metro.AppScope
import state.ControlScreenState

@CircuitInject(Route.Control::class, AppScope::class)
class ControlScreenUi: Ui<ControlScreenState> {
    @Composable
    override fun Content(state: ControlScreenState, modifier: Modifier) {
        Text(text = state.toString())
    }
}