package ru.android.currencyconverter.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.android.currencyconverter.domain.model.ConvertResult
import ru.android.currencyconverter.domain.repository.CloudRepository

class GetData(
    private val cloudRepository: CloudRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(to: String, from: String, amount: String):
            Flow<ConvertResult> = withContext(defaultDispatcher) {
        val items = cloudRepository.getData(to, from, amount)
        items
    }
}