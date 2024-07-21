package ru.android.currencyconverter.data.model

import com.google.gson.annotations.SerializedName
import ru.android.currencyconverter.domain.model.ConvertResult

data class ConvertResultDTO(
    @SerializedName("result") val value: Double
)

fun ConvertResultDTO.mapToDomain() = ConvertResult(data = value)