package com.example.apextest.infrastructure.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.apextest.domain.datasources.GetCharacterDatasource
import com.example.apextest.domain.repositories.GetCharacterRepository
import com.example.apextest.infrastructure.model.Character
import com.example.apextest.infrastructure.paging.CharactersPagingSource
import kotlinx.coroutines.flow.Flow

class GetCharacterRepositoryImpl(
    private val datasource: GetCharacterDatasource

) : GetCharacterRepository {

    override fun getCharacters(page: Int): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                prefetchDistance = 3,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharactersPagingSource(datasource) }
        ).flow;
    }

}