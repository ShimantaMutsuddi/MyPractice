package com.mutsuddi.mypractice.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutsuddi.mypractice.data.model.Characters
import com.mutsuddi.mypractice.data.repository.StarwarRepository
import com.mutsuddi.mypractice.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: StarwarRepository
): ViewModel(){

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharacters()
        }

    }

    val repositories: LiveData<Resource<List<Characters>>>
        get()=repository.characters

}