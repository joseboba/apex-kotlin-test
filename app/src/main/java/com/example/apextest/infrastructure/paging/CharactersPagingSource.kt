package com.example.apextest.infrastructure.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.apextest.domain.datasources.GetCharacterDatasource
import com.example.apextest.infrastructure.model.Character
import com.example.apextest.shared.infrastructure.RestResult

class CharactersPagingSource(
    private val datasource: GetCharacterDatasource
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1

        return try {
            when (val result = datasource.getCharacters(page)) {
                is RestResult.Success -> {
                    val totalPages = result.data.pageInfo.totalPages.toInt()
                    val characters = result.data.characters
                    LoadResult.Page(
                        data = characters,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (page >= totalPages) null else page + 1
                    )
                }

                is RestResult.Error -> {
                    LoadResult.Error(
                        Exception(result.message ?: "Request failed (http=${result.httpCode})")
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }
}
