package com.example.apextest.infrastructure.model
import java.time.Instant

data class CharactersModel (
    val pageInfo: PageInfo,
    val characters: List<Character>
)

data class PageInfo (
    val totalItems: Long = 0,
    val totalPages: Long = 0,
)

data class Character (
    val characterId: Long = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val image: String = "",
    val created: Instant = Instant.now()
)

