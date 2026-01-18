package com.example.apextest.infrastructure.mappers

import com.example.apextest.domain.entities.GetCharacterEntity
import com.example.apextest.domain.entities.Info as DomainInfo
import com.example.apextest.domain.entities.Result as DomainResult

import com.example.apextest.infrastructure.model.CharactersModel
import com.example.apextest.infrastructure.model.Character
import com.example.apextest.infrastructure.model.PageInfo
import java.time.Instant

object CharacterMapper {

    fun entityToModel(entity: GetCharacterEntity): CharactersModel {
        return CharactersModel(
            pageInfo = infoToModel(entity.info),
            characters = resultListToModel(entity.results)
        )
    }

    private fun infoToModel(info: DomainInfo): PageInfo {
        return PageInfo(
            totalItems = info.count,
            totalPages = info.pages
        )
    }

    private fun resultToModel(result: DomainResult): Character {
        return Character(
            characterId = result.id,
            name = result.name,
            status = result.status,
            species = result.species,
            type = result.type,
            gender = result.gender,
            image = result.image,
            created = Instant.parse(result.created)
        )
    }

    private fun resultListToModel(results: List<DomainResult>): List<Character> {
        return results.map(::resultToModel)
    }
}
