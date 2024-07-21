package ru.android.currencyconverter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.android.currencyconverter.data.api.RetrofitAPI
import ru.android.currencyconverter.data.repository.CloudRepositoryImpl
import ru.android.currencyconverter.domain.repository.CloudRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCloudRepositoryImpl(retrofitAPI: RetrofitAPI): CloudRepository {
        return CloudRepositoryImpl(retrofitAPI)
    }
}