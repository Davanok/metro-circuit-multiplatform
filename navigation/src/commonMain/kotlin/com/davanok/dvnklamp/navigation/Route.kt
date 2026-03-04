package com.davanok.dvnklamp.navigation

import com.slack.circuit.runtime.screen.Screen

@CommonParcelize
sealed interface Route : Screen, CommonParcelable {
    data object Control : Route
}