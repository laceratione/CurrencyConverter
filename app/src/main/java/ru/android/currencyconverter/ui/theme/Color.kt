package ru.android.currencyconverter.ui.theme

import androidx.compose.ui.graphics.Color

data class Colors(
    val primaryTextColor: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val errorTextColor: Color,
    val hintTextColor: Color
)

val lightPalette = Colors(
    primaryTextColor = Color(0xFF59442B),
    primaryBackground = Color.White,
    secondaryBackground = Color(0x10D0CCC7),
    errorTextColor = Color.Red,
    hintTextColor = Color(0xFFA0978C)
)