package com.example.apextest.config.interceptors

import com.example.apextest.domain.api.CharactersApi
import com.example.apextest.domain.datasources.GetCharacterDatasource
import com.example.apextest.domain.repositories.GetCharacterRepository
import com.example.apextest.infrastructure.datasources.GetCharacterDatasourceImpl
import com.example.apextest.infrastructure.repositories.GetCharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providerGetCharacterDatasource(
        api: CharactersApi
    ): GetCharacterDatasource = GetCharacterDatasourceImpl(api)

    @Provides
    @Singleton
    fun provideGetCharacterRepository(
        datasource: GetCharacterDatasource
    ): GetCharacterRepository = GetCharacterRepositoryImpl(datasource)

}