package ru.android.currencyconverter.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.android.currencyconverter.domain.model.ConvertResult
import ru.android.currencyconverter.domain.usecase.GetData
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getDataUseCase: GetData
) : ViewModel() {
    private val _result: MutableStateFlow<ConvertResult> = MutableStateFlow(ConvertResult())
    val result: StateFlow<ConvertResult> = _result.asStateFlow()

    private val _isProgress: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isProgress: StateFlow<Boolean> = _isProgress.asStateFlow()

    private val _amountError: MutableState<AmountError> = mutableStateOf(AmountError.Valid)
    val amountError: State<AmountError> = _amountError

    private fun checkAmoundError(amount: String) {
        when {
            amount.isEmpty() -> {
                _amountError.value = AmountError.Empty
            }

            amount.toDoubleOrNull() == null || amount.equals("0.0") || amount.equals("0") -> {
                _amountError.value = AmountError.Invalid
            }

            else -> {
                _amountError.value = AmountError.Valid
            }
        }
    }

    fun convert(from: String, to: String, amount: String) {
        checkAmoundError(amount)
        if (_amountError.value is AmountError.Valid) {
            viewModelScope.launch {
                _isProgress.value = true
                getDataUseCase(from = from, to = to, amount = amount)
                    .flowOn(Dispatchers.IO)
                    .catch { error ->
                        Log.d("myLogs", error.message.toString())
                        _isProgress.value = false
                    }
                    .collect {
                        _result.value = it
                        _isProgress.value = false
                    }
            }
        }
    }
}