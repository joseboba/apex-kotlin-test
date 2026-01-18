package com.example.apextest.presentation.state

import com.example.apextest.infrastructure.model.Character

data class HomeState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
    val currentPage: Int = 1,
    val isLastPage: Boolean = false
)
