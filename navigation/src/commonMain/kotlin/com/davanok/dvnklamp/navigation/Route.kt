package com.davanok.dvnklamp.navigation

import com.slack.circuit.runtime.screen.Screen

sealed interface Route : Screen {
    data object Control : Route
}