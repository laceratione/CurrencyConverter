package ru.android.currencyconverter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.android.currencyconverter.domain.repository.CloudRepository
import ru.android.currencyconverter.domain.usecase.GetData

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetData(cloudRepository: CloudRepository): GetData {
        return GetData(cloudRepository)
    }
}