package com.davanok.dvnklamp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.expressiveLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun AppTheme(
    onThemeChanged: (isDark: Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    val systemIsDark = isSystemInDarkTheme()
    LaunchedEffect(systemIsDark) {
        onThemeChanged(systemIsDark)
    }

    MaterialExpressiveTheme (
        colorScheme = if (systemIsDark) darkColorScheme() else expressiveLightColorScheme(),
        content = { Surface(content = content) }
    )
}
