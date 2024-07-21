package ru.android.currencyconverter.data.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.android.currencyconverter.BuildConfig
import ru.android.currencyconverter.data.model.ConvertResultDTO

private const val RATES_API_KEY = BuildConfig.RATES_API_KEY

interface RetrofitAPI {
    @Headers("apikey: $RATES_API_KEY")
    @GET("/exchangerates_data/convert?")
    suspend fun getData(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: String
    ): ConvertResultDTO
}