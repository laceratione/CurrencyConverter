package ru.android.currencyconverter.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.android.currencyconverter.domain.model.ConvertResult

interface CloudRepository {
    fun getData(to: String, from: String, amount: String): Flow<ConvertResult>
}