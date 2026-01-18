package com.example.apextest.domain.datasources

import com.example.apextest.infrastructure.model.CharactersModel
import com.example.apextest.shared.infrastructure.RestResult

interface GetCharacterDatasource {
    suspend fun getCharacters(page: Int = 1): RestResult<CharactersModel>;
}