package com.example.servicosaqui.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val EsquemaClaro = lightColorScheme(
    primary = Verde,
    onPrimary = Branco,
    primaryContainer = VerdeClaro,
    onPrimaryContainer = Verde,
    secondary = Dourado,
    onSecondary = Grafite,
    secondaryContainer = DouradoClaro,
    onSecondaryContainer = Grafite,
    background = Fundo,
    onBackground = Grafite,
    surface = Branco,
    onSurface = Grafite,
    surfaceVariant = VerdeClaro,
    onSurfaceVariant = CinzaTexto,
    surfaceContainerLowest = Branco,
    surfaceContainerLow = Branco,
    surfaceContainer = Branco,
    surfaceContainerHigh = Branco,
    surfaceContainerHighest = Branco,
    outline = CinzaBorda,
    outlineVariant = CinzaDivisor
)

@Composable
fun ServicosAquiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EsquemaClaro,
        typography = Typography,
        content = content
    )
}
