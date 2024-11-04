package com.example.d_dmaster.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

// Defina suas cores
private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF1EB980),
    secondary = Color(0xFF045D56)
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF00E676),
    secondary = Color(0xFF00C853)
)

@Composable
fun DDMasterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

// Você também pode adicionar uma prévia para ver o tema no Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewTheme() {
    DDMasterTheme {
        Text(text = "Hello, DD Master Theme!")
    }
}