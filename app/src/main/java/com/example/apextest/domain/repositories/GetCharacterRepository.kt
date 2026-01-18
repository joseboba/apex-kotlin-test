package com.example.apextest.domain.repositories

import androidx.paging.PagingData
import com.example.apextest.infrastructure.model.Character
import kotlinx.coroutines.flow.Flow

interface GetCharacterRepository {
    fun getCharacters(page: Int = 1): Flow<PagingData<Character>>
}