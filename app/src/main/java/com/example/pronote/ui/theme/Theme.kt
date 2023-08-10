package com.example.pronote.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = purple200,
    onPrimary = purple700,
    secondary = teal200,
    background = purpleD5
)

private val pastelColorPalette = lightColorScheme(
    primary = apricot,
    onPrimary = engLav,
    secondary = oldLav,
    background = melon
)

@Composable
fun proNoteComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = DarkColorPalette

    MaterialTheme(

        typography = typography,
        shapes = shapes,
        content = content
    )
}