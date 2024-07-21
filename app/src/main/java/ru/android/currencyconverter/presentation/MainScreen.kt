package ru.android.currencyconverter.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.android.currencyconverter.R
import ru.android.currencyconverter.ui.components.ExposedDropdownMenuBox
import ru.android.currencyconverter.ui.theme.AppTheme

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val currencies = stringArrayResource(id = R.array.currencies)

    val result = viewModel.result.collectAsState()
    val isProgress = viewModel.isProgress.collectAsState()

    val amount = remember { mutableStateOf("") }
    val currency = remember { mutableStateOf(currencies[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp),
            value = amount.value,
            onValueChange = { amount.value = it },
            label = { Text(text = stringResource(R.string.label_amount)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.placeholder_amount),
                    color = AppTheme.colors.hintTextColor
                )
            },
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = AppTheme.colors.primaryBackground,
                errorIndicatorColor = AppTheme.colors.errorTextColor,
                focusedIndicatorColor = AppTheme.colors.primaryBackground,
                unfocusedIndicatorColor = AppTheme.colors.primaryBackground,
                errorLabelColor = AppTheme.colors.errorTextColor,
                errorPlaceholderColor = AppTheme.colors.errorTextColor
            ),
            enabled = !isProgress.value,
            singleLine = true,
            isError = when (viewModel.amountError.value) {
                AmountError.Valid -> false
                else -> true
            },
            supportingText = {
                when (viewModel.amountError.value) {
                    AmountError.Empty -> Text(
                        text = stringResource(R.string.amount_empty_error),
                        color = AppTheme.colors.errorTextColor
                    )

                    AmountError.Invalid -> Text(
                        text = stringResource(R.string.amount_invalid_error),
                        color = AppTheme.colors.errorTextColor
                    )

                    else -> {}
                }
            },
            trailingIcon = {
                Text(text = "USD", fontSize = 20.sp)
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(R.string.title_currency), fontSize = 18.sp)
            ExposedDropdownMenuBox(currency, currencies, !isProgress.value)
        }
        Button(
            onClick = {
                viewModel.convert(
                    from = "USD",
                    to = currency.value,
                    amount = amount.value
                )
            },
            enabled = !isProgress.value
        ) {
            Text(text = stringResource(id = R.string.convert_button_text))
        }
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Результат: ${result.value.data}",
            fontSize = 20.sp
        )
    }
}