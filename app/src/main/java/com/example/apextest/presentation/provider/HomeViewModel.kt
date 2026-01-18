package com.example.apextest.presentation.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.apextest.domain.repositories.GetCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GetCharacterRepository
) : ViewModel() {

    val characters = repository.getCharacters()
        .cachedIn(viewModelScope)
}