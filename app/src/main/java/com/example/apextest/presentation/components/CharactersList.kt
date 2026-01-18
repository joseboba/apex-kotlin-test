package com.example.apextest.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.apextest.infrastructure.model.Character

@Composable
fun CharactersList(
    items: LazyPagingItems<Character>,
    listState: LazyListState
) {
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items.itemCount) { index ->
            val character = items[index]
            if (character != null) {
                CharacterCard(
                    character = character,
                    modifier = Modifier
                )
            }
        }

        item { AppendLoadStateItem(loadState = items.loadState.append) }
    }
}