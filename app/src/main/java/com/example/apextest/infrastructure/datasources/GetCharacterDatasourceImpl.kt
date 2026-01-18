package com.example.apextest.infrastructure.datasources

import com.example.apextest.domain.api.CharactersApi
import com.example.apextest.domain.datasources.GetCharacterDatasource
import com.example.apextest.domain.entities.toModel
import com.example.apextest.infrastructure.model.CharactersModel
import com.example.apextest.shared.infrastructure.RestResult
import kotlinx.coroutines.delay
import retrofit2.HttpException

class GetCharacterDatasourceImpl(
    private val api: CharactersApi
) : GetCharacterDatasource {

    override suspend fun getCharacters(page: Int): RestResult<CharactersModel> {
        return try {
            delay(2_000)
            val apiResponse = api.getCharacters(page)
            val responseModel = apiResponse.toModel()
            RestResult.Success(responseModel)
        } catch (e: HttpException) {
            RestResult.Error(e.message(), e.code())
        } catch (e: Exception) {
            RestResult.Error(e.message ?: "Unexpected exception", -1)
        }
    }

}