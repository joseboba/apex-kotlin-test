package com.example.apextest.domain.entities

import com.example.apextest.infrastructure.mappers.CharacterMapper
import com.example.apextest.infrastructure.model.CharactersModel
import com.squareup.moshi.JsonClass
import kotlinx.serialization.*
import java.time.Instant

@JsonClass(generateAdapter = true)
data class GetCharacterEntity (
    val info: Info,
    val results: List<Result>
)

@JsonClass(generateAdapter = true)
data class Info (
    val count: Long = 0,
    val pages: Long = 0,
    val next: String?,
    val prev: String?
)

@JsonClass(generateAdapter = true)
data class Result (
    val id: Long = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: Location,
    val location: Location,
    val image: String = "",
    val episode: List<String> = emptyList(),
    val url: String = "",
    val created: String = ""
)

@JsonClass(generateAdapter = true)
data class Location (
    val name: String = "",
    val url: String = ""
)

fun GetCharacterEntity.toModel(): CharactersModel = CharacterMapper.entityToModel(this)