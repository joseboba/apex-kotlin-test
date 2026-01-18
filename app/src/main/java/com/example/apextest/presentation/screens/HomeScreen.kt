package com.example.apextest.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.apextest.presentation.components.CharactersList
import com.example.apextest.presentation.provider.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(vm: HomeViewModel = hiltViewModel()) {
    val pageItems = vm.characters.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            ScrollToTopFab(listState = listState)
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val refresh = pageItems.loadState.refresh) {
                is LoadState.Loading -> CircularProgressIndicator()
                is LoadState.Error -> Text("Error: ${refresh.error.message}")
                else -> CharactersList(listState = listState, items = pageItems)
            }
        }
    }
}

@Composable
fun ScrollToTopFab(listState: LazyListState) {
    val scope = rememberCoroutineScope()
    val showFab = listState.firstVisibleItemIndex > 0

    if (showFab) {
        FloatingActionButton(
            onClick = {
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Scroll to top"
            )
        }
    }
}
