package ru.android.currencyconverter.presentation

sealed class AmountError {
    object Empty: AmountError()
    object Invalid: AmountError()
    object Valid: AmountError()
}