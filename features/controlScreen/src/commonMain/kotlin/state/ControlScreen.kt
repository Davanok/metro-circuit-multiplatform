package state

import androidx.compose.runtime.Immutable
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

@Immutable
interface ControlScreenState : CircuitUiState {

}
sealed interface ControlScreenEvent : CircuitUiEvent {

}