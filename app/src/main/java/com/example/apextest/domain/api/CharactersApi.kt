package com.example.apextest.domain.api

import com.example.apextest.domain.entities.GetCharacterEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int = 1): GetCharacterEntity;

}