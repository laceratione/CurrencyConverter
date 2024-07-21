package ru.android.currencyconverter.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.android.currencyconverter.data.api.RetrofitAPI
import ru.android.currencyconverter.data.model.mapToDomain
import ru.android.currencyconverter.domain.model.ConvertResult
import ru.android.currencyconverter.domain.repository.CloudRepository

class CloudRepositoryImpl(private val retrofitAPI: RetrofitAPI) : CloudRepository {
    override fun getData(to: String, from: String, amount: String): Flow<ConvertResult> = flow {
        emit(retrofitAPI.getData(to, from, amount).mapToDomain())
    }

}