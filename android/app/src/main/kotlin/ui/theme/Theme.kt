package com.deviceactivitytracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    onError = md_theme_dark_onError,
    errorContainer = md_theme_dark_errorContainer,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    errorContainer = md_theme_light_errorContainer,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

@Composable
fun DeviceActivityTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            if (darkTheme) dynamicDarkColorScheme(LocalContext.current) else dynamicLightColorScheme(LocalContext.current)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}

import androidx.compose.ui.platform.LocalContext

// Material Design 3 Colors
val md_theme_light_primary = androidx.compose.ui.graphics.Color(0xFF006E40)
val md_theme_light_onPrimary = androidx.compose.ui.graphics.Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = androidx.compose.ui.graphics.Color(0xFF7FFF90)
val md_theme_light_onPrimaryContainer = androidx.compose.ui.graphics.Color(0xFF002204)
val md_theme_light_secondary = androidx.compose.ui.graphics.Color(0xFF1B6D3A)
val md_theme_light_onSecondary = androidx.compose.ui.graphics.Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = androidx.compose.ui.graphics.Color(0xFFA2F5B4)
val md_theme_light_onSecondaryContainer = androidx.compose.ui.graphics.Color(0xFF002208)
val md_theme_light_tertiary = androidx.compose.ui.graphics.Color(0xFF386667)
val md_theme_light_onTertiary = androidx.compose.ui.graphics.Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = androidx.compose.ui.graphics.Color(0xFFBCECED)
val md_theme_light_onTertiaryContainer = androidx.compose.ui.graphics.Color(0xFF001F20)
val md_theme_light_error = androidx.compose.ui.graphics.Color(0xFFB3261E)
val md_theme_light_onError = androidx.compose.ui.graphics.Color(0xFFFFFFFF)
val md_theme_light_errorContainer = androidx.compose.ui.graphics.Color(0xFFF9DEDC)
val md_theme_light_onErrorContainer = androidx.compose.ui.graphics.Color(0xFF410E0B)
val md_theme_light_background = androidx.compose.ui.graphics.Color(0xFFFCFDF6)
val md_theme_light_onBackground = androidx.compose.ui.graphics.Color(0xFF1A1C1A)
val md_theme_light_surface = androidx.compose.ui.graphics.Color(0xFFFCFDF6)
val md_theme_light_onSurface = androidx.compose.ui.graphics.Color(0xFF1A1C1A)
val md_theme_light_surfaceVariant = androidx.compose.ui.graphics.Color(0xFFDEE5DF)
val md_theme_light_onSurfaceVariant = androidx.compose.ui.graphics.Color(0xFF404943)
val md_theme_light_outline = androidx.compose.ui.graphics.Color(0xFF717972)
val md_theme_light_outlineVariant = androidx.compose.ui.graphics.Color(0xFFC0C9C3)
val md_theme_light_scrim = androidx.compose.ui.graphics.Color(0xFF000000)

val md_theme_dark_primary = androidx.compose.ui.graphics.Color(0xFF7FFF90)
val md_theme_dark_onPrimary = androidx.compose.ui.graphics.Color(0xFF003D20)
val md_theme_dark_primaryContainer = androidx.compose.ui.graphics.Color(0xFF00582E)
val md_theme_dark_onPrimaryContainer = androidx.compose.ui.graphics.Color(0xFF7FFF90)
val md_theme_dark_secondary = androidx.compose.ui.graphics.Color(0xFF86D99A)
val md_theme_dark_onSecondary = androidx.compose.ui.graphics.Color(0xFF00390E)
val md_theme_dark_secondaryContainer = androidx.compose.ui.graphics.Color(0xFF004D1F)
val md_theme_dark_onSecondaryContainer = androidx.compose.ui.graphics.Color(0xFFA2F5B4)
val md_theme_dark_tertiary = androidx.compose.ui.graphics.Color(0xFFA0D0D2)
val md_theme_dark_onTertiary = androidx.compose.ui.graphics.Color(0xFF003739)
val md_theme_dark_tertiaryContainer = androidx.compose.ui.graphics.Color(0xFF1F4E50)
val md_theme_dark_onTertiaryContainer = androidx.compose.ui.graphics.Color(0xFFBCECED)
val md_theme_dark_error = androidx.compose.ui.graphics.Color(0xFFF2B8B5)
val md_theme_dark_onError = androidx.compose.ui.graphics.Color(0xFF601410)
val md_theme_dark_errorContainer = androidx.compose.ui.graphics.Color(0xFF8C1D18)
val md_theme_dark_onErrorContainer = androidx.compose.ui.graphics.Color(0xFFF9DEDC)
val md_theme_dark_background = androidx.compose.ui.graphics.Color(0xFF1A1C1A)
val md_theme_dark_onBackground = androidx.compose.ui.graphics.Color(0xFFE3E3E0)
val md_theme_dark_surface = androidx.compose.ui.graphics.Color(0xFF1A1C1A)
val md_theme_dark_onSurface = androidx.compose.ui.graphics.Color(0xFFE3E3E0)
val md_theme_dark_surfaceVariant = androidx.compose.ui.graphics.Color(0xFF404943)
val md_theme_dark_onSurfaceVariant = androidx.compose.ui.graphics.Color(0xFFC0C9C3)
val md_theme_dark_outline = androidx.compose.ui.graphics.Color(0xFF8A9390)
val md_theme_dark_outlineVariant = androidx.compose.ui.graphics.Color(0xFF404943)
val md_theme_dark_scrim = androidx.compose.ui.graphics.Color(0xFF000000)
