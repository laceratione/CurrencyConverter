package ru.android.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.android.currencyconverter.presentation.MainScreen
import ru.android.currencyconverter.presentation.MainViewModel
import ru.android.currencyconverter.ui.theme.CurrencyConverterTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel by viewModels()
        setContent {
            CurrencyConverterTheme {
                MainScreen(viewModel = mainViewModel)
            }
        }
    }
}

