package com.example.apextest.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState

@Composable
fun AppendLoadStateItem(loadState: LoadState) {
    when (loadState) {
        is LoadState.Loading -> Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator() }

        is LoadState.Error -> {
            Text(
                text = "Error cargando más: ${loadState.error.message}",
                modifier = Modifier.padding(16.dp)
            )
        }

        else -> Unit
    }
}